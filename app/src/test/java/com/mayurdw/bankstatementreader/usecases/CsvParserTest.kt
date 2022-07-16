package com.mayurdw.bankstatementreader.usecases

import com.mayurdw.bankstatementreader.data.csv.CsvItem
import com.mayurdw.bankstatementreader.data.csv.FakeFormattedCsvData
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.*
import java.time.Month

internal class CsvParserTest {

    private val folder: TemporaryFolder = TemporaryFolder()
    private lateinit var originalFile: File

    @Before
    fun setup() {
        this.folder.create()
        this.originalFile = this.folder.newFile("originalFile")
    }

    @After
    fun destroy() {
        this.folder.delete()
    }

    @Test
    fun sanityTests() {
        assertTrue(
            CsvParser.getCsvItems(this.originalFile.bufferedReader()).isEmpty()
        )
    }

    @Test
    fun getCsvItems_simpleCsvLine_successful() {
        this.originalFile.writeText("Date,Unique Id,Tran Type,Cheque Number,Payee,Memo,Amount\n" +
                "2022/06/08,2022060801,A/P,,\"CatSaver\",\"A/P rent\",-235.00")


        val listOfCsvItem: List<CsvItem> = CsvParser.getCsvItems(
            bufferedReader = this.originalFile.bufferedReader()
        )

        assertEquals(listOfCsvItem.size, 1)
        val csvItem = listOfCsvItem[0]

        assertEquals(csvItem.amount, -235.00, 0.0)
        assertTrue(csvItem.payMemo.contains("rent"))
        assertEquals(csvItem.payeeName, "CatSaver")
        assertEquals(csvItem.date.month, Month.JUNE)
    }

    @Test
    fun getCsvItems_fullFile_successful() {
        this.originalFile.writeText(FakeFormattedCsvData.csvString)
        val listOfCsvItem: List<CsvItem> = CsvParser.getCsvItems(
            bufferedReader = this.originalFile.bufferedReader()
        )

        assertEquals(listOfCsvItem.size, FakeFormattedCsvData.csvString.split("\n").size - 1)
    }
}