package com.mayurdw.bankstatementreader.data.csv

import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

internal class CsvParserTest {
    private lateinit var csvParser: CsvParser

    @Before
    fun setup() {
        this.csvParser = CsvParser()
    }

    @Test
    fun simpleParsingTest() {
        val listOfCsvItem: List<CsvItem> = csvParser.getCsvItems(FakeFormattedCsvData.csvString)

        assertTrue(listOfCsvItem.isNotEmpty())
    }
}