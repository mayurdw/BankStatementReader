package com.mayurdw.bankstatementreader.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mayurdw.bankstatementreader.data.csv.CsvParser
import com.mayurdw.bankstatementreader.model.Transaction
import com.mayurdw.bankstatementreader.model.TransactionCategory
import com.mayurdw.bankstatementreader.model.UnknownCategory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import java.io.InputStream
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
class Repository {
    private val csvParser: CsvParser = CsvParser()
    private val _totalIncome: MutableLiveData<Double> = MutableLiveData(0.0)
    private val _totalExpenses: MutableLiveData<Double> = MutableLiveData(0.0)

    val totalIncome : LiveData<Double>
        get() = _totalIncome
    val totalExpenses: LiveData<Double>
        get() = _totalExpenses

    val month: Month = Month.JULY

    fun receivedFile(inputStream: InputStream) {
        val csvItems = csvParser.getCsvItem(inputStream = inputStream)
        val transactions : List<Transaction> = csvItems.map {
            Transaction(
                amount = it.amount,
                date = it.date,
                payee = it.payeeName,
                memo = it.payMemo,
                category = TransactionCategory.Unknown(UnknownCategory.UNKNOWN)
            )
        }
        _totalExpenses.value = transactions.filter { it.amount < 0.0 }.sumOf { it.amount }
        _totalIncome.value = transactions.filter { it.amount > 0.0 }.sumOf { it.amount }
    }


}