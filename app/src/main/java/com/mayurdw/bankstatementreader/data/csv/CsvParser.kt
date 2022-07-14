package com.mayurdw.bankstatementreader.data.csv

import androidx.annotation.VisibleForTesting
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import timber.log.Timber
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CsvParser {
    private lateinit var csvItems: List<CsvItem>

    fun getCsvItem(inputStream: InputStream) : List<CsvItem> {
        var bufferedReader = BufferedReader(InputStreamReader(inputStream))

        bufferedReader = this.skipLines(bufferedReader)

        this.csvItems = getCsvItems(bufferedReader)

        return this.csvItems
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