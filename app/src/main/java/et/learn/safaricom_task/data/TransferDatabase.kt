package com.mobilemoney.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import android.content.Context
import com.mobilemoney.model.Transfer

@Database(
    entities = [Transfer::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class TransferDatabase : RoomDatabase() {
    abstract fun transferDao(): TransferDao

    companion object {
        @Volatile
        private var INSTANCE: TransferDatabase? = null

        fun getDatabase(context: Context): TransferDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TransferDatabase::class.java,
                    "transfer_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
