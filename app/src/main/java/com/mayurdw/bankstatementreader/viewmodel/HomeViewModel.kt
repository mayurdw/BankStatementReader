package com.mayurdw.bankstatementreader.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mayurdw.bankstatementreader.data.DataState
import com.mayurdw.bankstatementreader.data.Repository
import com.mayurdw.bankstatementreader.util.formatInCurrencyFormat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import java.time.LocalDate
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
    private val _totalExpenses: MutableLiveData<String> = MutableLiveData()
    private val _totalIncome: MutableLiveData<String> = MutableLiveData()

    val totalExpenses: LiveData<String> = _totalExpenses
    val totalIncome: LiveData<String> = _totalIncome

    val month: String = LocalDate.now().month.toString()

    init {
        viewModelScope.launch {
            repository.getTransactions().onEach { dataState ->
                when (dataState) {
                    is DataState.Success -> {
                        _totalExpenses.value =
                            dataState.data.filter { it.amount < 0.0 }.sumOf { it.amount }
                                .formatInCurrencyFormat()
                        _totalIncome.value =
                            dataState.data.filter { it.amount > 0.0 }.sumOf { it.amount }
                                .formatInCurrencyFormat()
                    }
                    else -> {
                        Timber.d("Unexpected value $dataState")
                    }
                }
            }
                .launchIn(viewModelScope)
        }
    }
}