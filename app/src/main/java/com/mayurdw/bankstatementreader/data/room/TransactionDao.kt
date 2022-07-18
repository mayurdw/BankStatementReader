package com.mayurdw.bankstatementreader.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Insert
    suspend fun insertTransactions(transactionList: List<TransactionDTO>)

    @Query("SELECT * from transactions ORDER BY date DESC")
    fun getAllTransactions() : Flow<List<TransactionDTO>>
}