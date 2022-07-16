package com.mayurdw.bankstatementreader

import com.mayurdw.bankstatementreader.data.DataState
import com.mayurdw.bankstatementreader.model.Transaction
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.Month

interface IRepository {
    suspend fun insertTransactions(transactionList: List<Transaction>)

    suspend fun getTransactions(month: Month = LocalDate.now().month) : Flow<DataState<List<Transaction>>>
}