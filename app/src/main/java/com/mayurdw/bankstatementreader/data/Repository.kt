package com.mayurdw.bankstatementreader.data

import com.mayurdw.bankstatementreader.IRepository
import com.mayurdw.bankstatementreader.data.mapper.RoomMapper
import com.mayurdw.bankstatementreader.data.room.TransactionDao
import com.mayurdw.bankstatementreader.model.Transaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.time.Month

/**
 * Will handle storing of data and retriving of data
 *
 * TODO:
 *  - Use State/Shared flow
 *  - Setup UI Tests
 *  - Setup Dispatchers
 * */
class Repository(
    private val transactionDao: TransactionDao,
    private val roomMapper: RoomMapper
) : IRepository {
    override suspend fun insertTransactions(transactionList: List<Transaction>) {
        withContext(Dispatchers.IO) {
            transactionDao.insertTransactions(
                roomMapper.mapToEntities(transactionList)
            )
        }
    }

    override suspend fun getTransactions(month: Month): Flow<DataState<List<Transaction>>> = flow {
        emit(DataState.Success(roomMapper.mapFromEntities(transactionDao.getAllTransactions())))
    }
}