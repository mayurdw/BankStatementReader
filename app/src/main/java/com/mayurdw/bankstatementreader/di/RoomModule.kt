package com.mayurdw.bankstatementreader.di

import android.content.Context
import androidx.room.Room
import com.mayurdw.bankstatementreader.data.room.TransactionDao
import com.mayurdw.bankstatementreader.data.room.TransactionDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomModule {
    @Singleton
    @Provides
    fun provideTransactionDatabase(@ApplicationContext context: Context): TransactionDatabase {
        return Room.databaseBuilder(
            context,
            TransactionDatabase::class.java,
            TransactionDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()

    }

    @Singleton
    @Provides
    fun provideTransactionDao(transactionDatabase: TransactionDatabase): TransactionDao =
        transactionDatabase.transactionDao()
}