package com.mobilemoney.data

import androidx.room.TypeConverter
import com.mobilemoney.model.TransferStatus

class Converters {
    @TypeConverter
    fun fromTransferStatus(status: TransferStatus): String {
        return status.name
    }

    @TypeConverter
    fun toTransferStatus(status: String): TransferStatus {
        return TransferStatus.valueOf(status)
    }
}
