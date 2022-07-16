package com.mayurdw.bankstatementreader.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [TransactionDTO::class],
    exportSchema = true,
    version = 1
)
abstract class TransactionDatabase : RoomDatabase() {
    abstract fun transactionDao() : TransactionDao

    companion object {
        const val DATABASE_NAME = "transaction_db"
    }
}