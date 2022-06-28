package com.mayurdw.bankstatementreader

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    /* Mutable Live Data */
    private val _totalExpenses : MutableLiveData<String> = MutableLiveData()
    private val _totalIncome : MutableLiveData<String> = MutableLiveData()

    /* Live Data */
    val totalExpenses : LiveData<String>
        get() = this._totalExpenses
    val totalIncome : LiveData<String>
        get() = this._totalIncome

    init {
        // Load data
        val repository = Repository()

        _totalExpenses.value = repository.totalExpenses
        _totalIncome.value = repository.totalIncome
    }
}