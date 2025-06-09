package com.mobilemoney.data

import com.mobilemoney.model.Transfer
import com.mobilemoney.model.TransferStatus
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransferRepository @Inject constructor(
    private val transferDao: TransferDao
) {

    suspend fun getAllTransfers(): List<Transfer> {
        // Check if database is empty and seed with sample data
        val transfers = transferDao.getAllTransfers()
        if (transfers.isEmpty()) {
            seedSampleData()
            return transferDao.getAllTransfers()
        }
        return transfers
    }

    suspend fun getTransferById(id: String): Transfer? {
        return transferDao.getTransferById(id)
    }

    suspend fun insertTransfer(transfer: Transfer) {
        transferDao.insertTransfer(transfer)
    }

    private suspend fun seedSampleData() {
        val sampleTransfers = listOf(
            Transfer(
                id = "TXN001",
                recipientName = "Commercial Bank of Ethiopia",
                amount = 500.0,
                dateTime = "2024-01-15 10:30 AM",
                status = TransferStatus.SUCCESS
            ),
            Transfer(
                id = "TXN002",
                recipientName = "Dashen Bank",
                amount = 250.0,
                dateTime = "2024-01-14 02:15 PM",
                status = TransferStatus.SUCCESS
            ),
            Transfer(
                id = "TXN003",
                recipientName = "Bank of Abyssinia",
                amount = 100.0,
                dateTime = "2024-01-13 09:45 AM",
                status = TransferStatus.PENDING
            ),
            Transfer(
                id = "TXN004",
                recipientName = "Awash Bank",
                amount = 75.0,
                dateTime = "2024-01-12 04:20 PM",
                status = TransferStatus.FAILED
            ),
            Transfer(
                id = "TXN005",
                recipientName = "United Bank",
                amount = 300.0,
                dateTime = "2024-01-11 11:10 AM",
                status = TransferStatus.SUCCESS
            )
        )
        transferDao.insertTransfers(sampleTransfers)
    }
}
