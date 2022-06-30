package com.mayurdw.bankstatementreader.data.csv

import java.time.LocalDate

/**
 * Each variable corresponds to ASB's CSV file type
 * Note: This doesn't include the data above which contains bank information
 * */
data class CsvItem (
    val date: LocalDate,
    val uniqueId: Int,
    val transType: String,
    val chequeNumber: String,
    val payeeName: String,
    val payMemo: String,
    val amount: Double,
)