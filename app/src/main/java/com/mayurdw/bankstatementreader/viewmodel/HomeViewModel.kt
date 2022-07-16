package com.mayurdw.bankstatementreader.viewmodel

import androidx.lifecycle.*
import com.mayurdw.bankstatementreader.data.Repository
import com.mayurdw.bankstatementreader.model.Transaction
import com.mayurdw.bankstatementreader.util.formatInCurrencyFormat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.Month
import javax.inject.Inject

/**
 * Will be responsible for handling UI logic
 *
 * TODO:
 *  - Use coroutines and flows
 * */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    /* MutableLiveData */
//    private val _totalExpenses: MutableLiveData<String> =
//        MutableLiveData(0.0.formatInCurrencyFormat())
//    private val _totalIncome: MutableLiveData<String> =
//        MutableLiveData(0.0.formatInCurrencyFormat())

    /* Live Data */
    val totalExpenses: LiveData<String> = liveData {
        repository.getTransactions().collect { transactionList ->
            emit(transactionList.filter { it.amount < 0.0 }.sumOf { it.amount }.formatInCurrencyFormat())
        }
    }

    val totalIncome: LiveData<String> = liveData {
        repository.getTransactions().collect() { transactionList ->
            emit(transactionList.filter { it.amount > 0.0 }.sumOf{ it.amount }.formatInCurrencyFormat())
        }
    }

    val month: String = LocalDate.now().month.toString()

    init {
        // Load data
        viewModelScope.launch {
            var totalIncome: String
            var totalExpenses: String
            withContext(Dispatchers.Default) {
                repository.getTransactions()
                    .collect { transactionList ->
                        totalExpenses = transactionList.filter { it.amount < 0.0 }.sumOf { it.amount }
                            .formatInCurrencyFormat()
                        totalIncome = transactionList.filter { it.amount > 0.0 }.sumOf { it.amount }
                            .formatInCurrencyFormat()
                    }


//                _totalIncome.value = totalIncome
//                _totalExpenses.value = totalExpenses
            }
        }
    }
}

sealed class HomeState {
    data class Success(val transactionList: List<Transaction>) : HomeState()
    data class Failure(val exception: Throwable) : HomeState()
    object Loading : HomeState()
}