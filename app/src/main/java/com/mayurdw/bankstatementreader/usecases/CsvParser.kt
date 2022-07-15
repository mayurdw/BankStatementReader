package com.mayurdw.bankstatementreader.usecases

import androidx.annotation.VisibleForTesting
import com.mayurdw.bankstatementreader.IRepository
import com.mayurdw.bankstatementreader.data.csv.CsvItem
import com.mayurdw.bankstatementreader.model.Transaction
import com.mayurdw.bankstatementreader.model.TransactionCategory
import com.mayurdw.bankstatementreader.model.UnknownCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import timber.log.Timber
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class CsvParser {
    @Inject lateinit var repository: IRepository


    suspend fun parse(
        inputStream: InputStream
    ) {
        withContext(Dispatchers.Default) {
            repository.insertTransactions(
                getCsvItem(inputStream = inputStream).map { csvItem ->
                    Transaction(
                        amount = csvItem.amount,
                        date = csvItem.date,
                        payee = csvItem.payeeName,
                        memo = csvItem.payMemo,
                        category = TransactionCategory.Unknown(UnknownCategory.UNKNOWN)
                    )
                }
            )
        }
    }

    private fun getCsvItem(inputStream: InputStream) : List<CsvItem> {
        val csvItems: List<CsvItem>
        var bufferedReader = BufferedReader(InputStreamReader(inputStream))

        bufferedReader = this.skipLines(bufferedReader)

        csvItems = getCsvItems(bufferedReader)

        return csvItems
    }

    private fun skipLines(bufferedReader: BufferedReader) : BufferedReader {
        var i = 0

        while( i< 5){
            bufferedReader.readLine()
            i++
        }

        return bufferedReader
    }


    @VisibleForTesting
    fun getCsvItems(bufferedReader: BufferedReader): List<CsvItem> {
        val csvParser = CSVParser(
            bufferedReader,
            CSVFormat.DEFAULT
                .withFirstRecordAsHeader()
                .withTrim()
                .withIgnoreHeaderCase()
                .withIgnoreEmptyLines()
        )


        val list: MutableList<CsvItem> = mutableListOf()

        for (csvRecord in csvParser) {
            val csvItem = CsvItem(
                date = this.formatDate(csvRecord.get("Date")),
                uniqueId = csvRecord.get("Unique Id").toInt(),
                transType = csvRecord.get("Tran Type"),
                chequeNumber = csvRecord.get("Cheque Number"),
                payeeName = csvRecord.get("Payee").replace("\"", ""),
                payMemo = csvRecord.get("Memo").replace("\"", ""),
                amount = csvRecord.get("Amount").toDouble()
            )
            Timber.d("Adding $csvItem")
            list.add(
                csvItem
            )
        }

        return list
    }

    private fun formatDate(dateString: String): LocalDate {
        val firstApiFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd")
        return LocalDate.parse(dateString, firstApiFormat)
    }
}