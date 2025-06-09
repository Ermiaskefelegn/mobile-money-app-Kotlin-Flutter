package et.learn.safaricom_task

//package com.mobilemoney.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mobilemoney.data.TransferDao
import com.mobilemoney.data.TransferDatabase
import com.mobilemoney.model.Transfer
import com.mobilemoney.model.TransferStatus
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class TransferDaoTest {

    private lateinit var database: TransferDatabase
    private lateinit var transferDao: TransferDao

    private val sampleTransfer = Transfer(
        id = "TXN001",
        recipientName = "Commercial Bank of Ethiopia",
        amount = 500.0,
        dateTime = "2024-01-15 10:30 AM",
        status = TransferStatus.SUCCESS
    )

    @Before
    fun createDb() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TransferDatabase::class.java
        ).build()
        transferDao = database.transferDao()
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun insertAndGetTransfer() = runTest {
        transferDao.insertTransfer(sampleTransfer)

        val retrieved = transferDao.getTransferById("TXN001")

        assertEquals(sampleTransfer, retrieved)
    }

    @Test
    fun getAllTransfers_returnsAllInsertedTransfers() = runTest {
        val transfers = listOf(
            sampleTransfer,
            sampleTransfer.copy(id = "TXN002", recipientName = "Dashen Bank")
        )

        transferDao.insertTransfers(transfers)

        val allTransfers = transferDao.getAllTransfers()

        assertEquals(2, allTransfers.size)
        assertTrue(allTransfers.contains(transfers[0]))
        assertTrue(allTransfers.contains(transfers[1]))
    }

    @Test
    fun getTransferById_returnsNullForNonExistentId() = runTest {
        val result = transferDao.getTransferById("NON_EXISTENT")

        assertNull(result)
    }

    @Test
    fun insertTransfer_replacesExistingTransfer() = runTest {
        transferDao.insertTransfer(sampleTransfer)

        val updatedTransfer = sampleTransfer.copy(amount = 1000.0)
        transferDao.insertTransfer(updatedTransfer)

        val retrieved = transferDao.getTransferById("TXN001")

        retrieved?.amount?.let { assertEquals(1000.0, it, 0.01) }
    }

    @Test
    fun getAllTransfers_returnsEmptyListWhenNoTransfers() = runTest {
        val transfers = transferDao.getAllTransfers()

        assertTrue(transfers.isEmpty())
    }
}
