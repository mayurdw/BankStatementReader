package com.mayurdw.bankstatementreader.data.mapper

import com.mayurdw.bankstatementreader.data.room.TransactionDTO
import com.mayurdw.bankstatementreader.model.Transaction
import com.mayurdw.bankstatementreader.model.TransactionCategory
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class RoomMapper @Inject constructor() : EntityMapper<TransactionDTO, Transaction> {
    override fun mapFromEntity(entity: TransactionDTO): Transaction {
        return Transaction(
            amount = entity.amount,
            payee = entity.payee,
            date = LocalDate.parse(entity.date, DateTimeFormatter.ofPattern("yyyy/MM/dd")),
            memo = entity.memo,
            category = TransactionCategory.valueOf( entity.category )
        )
    }

    override fun mapToEntity(domainModel: Transaction): TransactionDTO {
        return TransactionDTO(
            amount = domainModel.amount,
            payee = domainModel.payee,
            memo = domainModel.memo,
            date = DateTimeFormatter.ofPattern("yyyy/MM/dd").format(domainModel.date),
            category = domainModel.category.toString()
        )
    }
}