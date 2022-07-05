package com.mayurdw.bankstatementreader.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mayurdw.bankstatementreader.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.Month
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    repository: Repository
): ViewModel() {
    /* MutableLiveData */
    private val _totalExpenses : MutableLiveData<String> = MutableLiveData()
    private val _totalIncome : MutableLiveData<String> = MutableLiveData()
    private val _month : MutableLiveData<Month> = MutableLiveData(Month.APRIL)
    /* Live Data */
    val totalExpenses : LiveData<String>
        get() = this._totalExpenses
    val totalIncome : LiveData<String>
        get() = this._totalIncome
    val month: LiveData<Month>
        get() = this._month

    init {
        // Load data
        _totalExpenses.value = repository.totalExpenses
        _totalIncome.value = repository.totalIncome
        _month.value = repository.month
    }
}