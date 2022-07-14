package com.mayurdw.bankstatementreader.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mayurdw.bankstatementreader.data.Repository
import com.mayurdw.bankstatementreader.util.formatInCurrencyFormat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
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
): ViewModel() {
    /* MutableLiveData */
    private val _month : MutableLiveData<Month> = MutableLiveData(Month.APRIL)
    /* Live Data */
    val totalExpenses : LiveData<Double>
        get() = repository.totalExpenses
    val totalIncome : LiveData<Double>
        get() = repository.totalIncome
    val month: LiveData<Month>
        get() = this._month

    init {
        // Load data
        _month.value = repository.month
    }
}