package com.mobilemoney.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mobilemoney.model.Transfer

@Dao
interface TransferDao {
    @Query("SELECT * FROM transfers ORDER BY dateTime DESC")
    suspend fun getAllTransfers(): List<Transfer>

    @Query("SELECT * FROM transfers WHERE id = :id")
    suspend fun getTransferById(id: String): Transfer?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransfer(transfer: Transfer)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransfers(transfers: List<Transfer>)
}
