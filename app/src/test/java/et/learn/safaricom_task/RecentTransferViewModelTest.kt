package et.learn.safaricom_task


import com.mobilemoney.data.TransferDao
import com.mobilemoney.data.TransferRepository
import com.mobilemoney.model.Transfer
import com.mobilemoney.model.TransferStatus
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class TransferRepositoryTest {

    @Mock
    private lateinit var transferDao: TransferDao

    private lateinit var repository: TransferRepository

    private val sampleTransfer = Transfer(
        id = "TXN001",
        recipientName = "Commercial Bank of Ethiopia",
        amount = 500.0,
        dateTime = "2024-01-15 10:30 AM",
        status = TransferStatus.SUCCESS
    )

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = TransferRepository(transferDao)
    }

    @Test
    fun `getAllTransfers should return transfers from dao when not empty`() = runTest {
        val transfers = listOf(sampleTransfer)
        whenever(transferDao.getAllTransfers()).thenReturn(transfers)

        val result = repository.getAllTransfers()

        assertEquals(transfers, result)
        verify(transferDao).getAllTransfers()
    }

    @Test
    fun `getAllTransfers should seed data when dao returns empty list`() = runTest {
        whenever(transferDao.getAllTransfers())
            .thenReturn(emptyList()) // First call returns empty
            .thenReturn(listOf(sampleTransfer)) // Second call returns seeded data

        val result = repository.getAllTransfers()

        verify(transferDao).insertTransfers(org.mockito.kotlin.any())
        assertEquals(listOf(sampleTransfer), result)
    }

    @Test
    fun `getTransferById should return transfer from dao`() = runTest {
        whenever(transferDao.getTransferById("TXN001")).thenReturn(sampleTransfer)

        val result = repository.getTransferById("TXN001")

        assertEquals(sampleTransfer, result)
        verify(transferDao).getTransferById("TXN001")
    }

    @Test
    fun `getTransferById should return null when transfer not found`() = runTest {
        whenever(transferDao.getTransferById("INVALID")).thenReturn(null)

        val result = repository.getTransferById("INVALID")

        assertNull(result)
        verify(transferDao).getTransferById("INVALID")
    }

    @Test
    fun `insertTransfer should call dao insertTransfer`() = runTest {
        repository.insertTransfer(sampleTransfer)

        verify(transferDao).insertTransfer(sampleTransfer)
    }
}
