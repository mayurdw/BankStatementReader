package com.mayurdw.bankstatementreader.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionDTO(
    @PrimaryKey(autoGenerate = false)
    var transactionID: Long,
    var amount: Double,
    var payee: String,
    var memo: String,
    var date: String,
    var category: String
)
