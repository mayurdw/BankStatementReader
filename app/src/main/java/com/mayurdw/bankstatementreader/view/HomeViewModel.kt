package com.mayurdw.bankstatementreader.view

import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mayurdw.bankstatementreader.Repository
import java.time.Month

class HomeViewModel : ViewModel() {
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
        val repository = Repository()

        _totalExpenses.value = repository.totalExpenses
        _totalIncome.value = repository.totalIncome
        _month.value = repository.month
    }
}