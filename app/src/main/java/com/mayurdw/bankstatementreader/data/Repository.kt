package com.mayurdw.bankstatementreader.data

import com.mayurdw.bankstatementreader.data.csv.CsvParser
import java.time.Month
import javax.inject.Inject

class Repository {
    @Inject lateinit var csvParser : CsvParser

    val totalIncome: String = "$300.00"
    val totalExpenses: String = "$500.00"
    val month: Month = Month.APRIL
}