package com.mayurdw.bankstatementreader.data.csv

import androidx.annotation.VisibleForTesting
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CsvParser {
    @VisibleForTesting
    fun getCsvItems(csvString: String): List<CsvItem> {
        if (csvString.isBlank())
            return emptyList()

        val list: MutableList<CsvItem> = mutableListOf()

        for (line: String in csvString.split("\n")) {
            val items = line.split(",")

            list.add(
                CsvItem(
                    date = this.formatDate(items[0]),
                    uniqueId = items[1].toInt(),
                    transType = items[2],
                    chequeNumber = items[3],
                    payeeName = items[4].replace("\"",""),
                    payMemo = items[5].replace("\"",""),
                    amount = items[6].toDouble()
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