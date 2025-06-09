package com.mobilemoney.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "transfers")
data class Transfer(
    @PrimaryKey val id: String,
    val recipientName: String,
    val amount: Double,
    val dateTime: String,
    val status: TransferStatus
)

enum class TransferStatus {
    SUCCESS, PENDING, FAILED
}

data class RecentTransfersUiState(
    val transfers: List<Transfer> = emptyList(),
    val isLoading: Boolean = false
)
