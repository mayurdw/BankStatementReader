package com.mayurdw.bankstatementreader.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mayurdw.bankstatementreader.Repository

class HomeViewModel : ViewModel() {
    /* MutableLiveData */
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