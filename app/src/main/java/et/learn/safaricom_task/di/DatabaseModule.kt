package com.mobilemoney.di

import android.content.Context
import androidx.room.Room
import com.mobilemoney.data.TransferDao
import com.mobilemoney.data.TransferDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideTransferDatabase(@ApplicationContext context: Context): TransferDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            TransferDatabase::class.java,
            "transfer_database"
        ).build()
    }

    @Provides
    fun provideTransferDao(database: TransferDatabase): TransferDao {
        return database.transferDao()
    }
}
