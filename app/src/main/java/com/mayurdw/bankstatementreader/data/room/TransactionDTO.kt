package com.mayurdw.bankstatementreader.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "transactions")
data class TransactionDTO(
    @PrimaryKey(autoGenerate = true)
    var transactionID: Long = 0L,
    var amount: Double,
    var payee: String,
    var memo: String,
    var date: String,
    var category: String
)
