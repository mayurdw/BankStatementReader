package com.mayurdw.bankstatementreader.data

import com.mayurdw.bankstatementreader.data.csv.CsvParser
import timber.log.Timber
import java.io.InputStream
import java.time.Month

class Repository {
    private val csvParser : CsvParser = CsvParser()

    val totalIncome: String = "$300.00"
    val totalExpenses: String = "$500.00"
    val month: Month = Month.APRIL

    fun readFile(inputStream: InputStream){
        val csvItems = csvParser.getCsvItem(inputStream = inputStream)

        Timber.d("${csvItems.size}")
    }
}