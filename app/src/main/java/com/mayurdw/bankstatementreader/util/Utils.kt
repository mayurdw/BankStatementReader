package com.mayurdw.bankstatementreader.util

import java.text.DecimalFormat

fun Double.formatInCurrencyFormat(): String {
    val format = DecimalFormat("$#,###.00")
    format.isDecimalSeparatorAlwaysShown = false
    return format.format(this).toString()
}