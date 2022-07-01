package com.mayurdw.bankstatementreader.data.csv

import androidx.annotation.VisibleForTesting
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.nio.file.Paths
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CsvParser {

    @VisibleForTesting
    fun reformatInputFile(bufferedReader: BufferedReader, file: File) {
        val temporaryFileReader = BufferedWriter(FileWriter(file))
        val numberOfLines = 5
        var i = 0
        var currentLine = bufferedReader.readLine()

        while (currentLine != null) {
            i++
            if (i > numberOfLines) {
                temporaryFileReader.write(currentLine + System.lineSeparator())
            }
            currentLine = bufferedReader.readLine()
        }

        temporaryFileReader.close()
        bufferedReader.close()
    }

    @VisibleForTesting
    fun getCsvItems(file: File): List<CsvItem> {
        val csvParser = CSVParser(
            file.bufferedReader(),
            CSVFormat.DEFAULT
                .withFirstRecordAsHeader()
                .withTrim()
                .withIgnoreHeaderCase()
                .withIgnoreEmptyLines()
        )


        val list: MutableList<CsvItem> = mutableListOf()

        for (csvRecord in csvParser) {
            list.add(
                CsvItem(
                    date = this.formatDate(csvRecord.get("Date")),
                    uniqueId = csvRecord.get("Unique Id").toInt(),
                    transType = csvRecord.get("Tran Type"),
                    chequeNumber = csvRecord.get("Cheque Number"),
                    payeeName = csvRecord.get("Payee").replace("\"", ""),
                    payMemo = csvRecord.get("Memo").replace("\"", ""),
                    amount = csvRecord.get("Amount").toDouble()
                )
            )
        }

        return list
    }

    private fun formatDate(dateString: String): LocalDate {
        val firstApiFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd")
        return LocalDate.parse(dateString, firstApiFormat)
    }
}