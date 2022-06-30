package com.mayurdw.bankstatementreader.data.csv

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.time.Month

internal class CsvParserTest {
    private lateinit var csvParser: CsvParser

    @Before
    fun setup() {
        this.csvParser = CsvParser()
    }

    @Test
    fun sanityTests(){
        assertTrue( csvParser.getCsvItems("").isEmpty() )
    }

    @Test
    fun getCsvItems_simpleCsvLine_successful() {
        val listOfCsvItem: List<CsvItem> = csvParser.getCsvItems(
            "2022/06/08,2022060801,A/P,,\"CatSaver\",\"A/P rent\",-235.00"
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
        val listOfCsvItem : List<CsvItem> = csvParser.getCsvItems(
            FakeFormattedCsvData.csvString
        )

        assertEquals( listOfCsvItem.size, FakeFormattedCsvData.csvString.split("\n").size)
    }
}