package com.mayurdw.bankstatementreader

import java.text.DecimalFormat
import java.time.LocalDate
import java.time.Month
import java.time.format.DateTimeFormatter

class Repository {
    private val csvString = "2022/06/08,2022060801,A/P,,\"CatSaver\",\"A/P rent\",-235.00\n" +
            "2022/06/08,2022060802,EFTPOS,,\"CW AKL LOWERQUEEN ST AUCKLAND\",\"EFTPOS\",-2.49\n" +
            "2022/06/08,2022060803,DEBIT,,\"DEBIT\",\"FC38-9020-0796625-01 groceries\",-3.14\n" +
            "2022/06/08,2022060804,DEBIT,,\"DEBIT\",\"FC38-9020-0796625-01 water\",-12.11\n" +
            "2022/06/08,2022060805,DEBIT,,\"DEBIT\",\"FC38-9020-0796625-01 eatingOut\",-17.30\n" +
            "2022/06/09,2022060901,EFTPOS,,\"RENKON-CITYAUCKLAND\",\"EFTPOS\",-16.00\n" +
            "2022/06/10,2022061001,A/P,,\"Sharesies NZ\",\"A/P Sharesies  Investment\",-10.00\n" +
            "2022/06/10,2022061002,EFTPOS,,\"MELLOWAUCKLAND\",\"EFTPOS\",-5.80\n" +
            "2022/06/10,2022061003,DEBIT,,\"DEBIT\",\"CARD 7515 Steam Restau rant Auck Mt Eden\",-19.00\n" +
            "2022/06/12,2022061201,DEBIT,,\"DEBIT\",\"CARD 7515 THE BASEMENT  AUCKLAND\",-4.50\n" +
            "2022/06/12,2022061202,DEBIT,,\"DEBIT\",\"FC38-9020-0796625-01 groceries\",-11.90\n" +
            "2022/06/12,2022061203,DEBIT,,\"DEBIT\",\"FC06-0158-0524883-00 groceries\",-47.84\n" +
            "2022/06/15,2022061501,A/P,,\"CatSaver\",\"A/P rent\",-235.00\n" +
            "2022/06/15,2022061502,DEBIT,,\"DEBIT\",\"CARD 7515 SKINNY  AUCKLAND\",-9.00\n" +
            "2022/06/15,2022061503,DEBIT,,\"DEBIT\",\"FC06-0158-0524883-00 thenk\",50.00"

    lateinit var totalIncome: String
    lateinit var totalExpenses: String
    lateinit var month: Month

    init {
        calculateAmounts( getCsvItems() )
    }

    private fun calculateAmounts( csvItems: List<CsvItem> ) {
        var expenses = 0.0
        var income = 0.0

        for( item in csvItems ){
            val amount: Double = item.amount.toDouble()

            if( amount > 0.0 ){
                income += amount
            } else {
                expenses += amount
            }
        }

        totalExpenses = expenses.convert()
        totalIncome = income.convert()
    }

    private fun Double.convert(): String {
        val format = DecimalFormat("$#,###.00")
        format.isDecimalSeparatorAlwaysShown = false
        return format.format(this).toString()
    }

    private fun getCsvItems() : List<CsvItem> {
        val mutableList: MutableList<CsvItem> = mutableListOf()

        for( line in csvString.split("\n")){
            val items = line.split(",")

            val firstApiFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd")
            val date = LocalDate.parse(items[0] , firstApiFormat)
            month = date.month

            val csvItem = CsvItem(
                amount = items[6]
            )

            mutableList.add(csvItem)
        }

        return mutableList
    }
}