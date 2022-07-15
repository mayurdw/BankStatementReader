package com.mayurdw.bankstatementreader.data

import com.mayurdw.bankstatementreader.IRepository
import com.mayurdw.bankstatementreader.model.Transaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.withContext
import java.time.Month

/**
 * Will handle storing of data and retriving of data
 *
 * TODO:
 *  - Need to figure out if we need a use case for CSV data
 *  - Need room data to be stored
 *  - Need to set a state so that View can navigate
 *  - Use State/Shared flow
 *  - Setup UI Tests
 *  - Setup Dispatchers
 * */
class Repository : IRepository {
    private val transactionList: MutableStateFlow<MutableList<Transaction>> = MutableStateFlow(
        mutableListOf()
    )

    override suspend fun insertTransactions(transactionList: List<Transaction>) {
        withContext(Dispatchers.Default){
            this@Repository.transactionList.value.addAll(0, transactionList)
        }
    }

    override suspend fun getTransactions(month: Month) : StateFlow<List<Transaction>> {
        return transactionList
    }


}