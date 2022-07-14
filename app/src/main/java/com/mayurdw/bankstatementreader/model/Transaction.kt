package com.mayurdw.bankstatementreader.model

import java.time.LocalDate

/**
 * Need to figure out a way to make Transaction Category defined by users? Not sure if I want to do that
 **/
data class Transaction(
    val amount: Double,
    val date: LocalDate,
    val payee: String,
    val memo: String,
    val category: TransactionCategory
)
