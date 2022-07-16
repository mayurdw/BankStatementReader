package com.mayurdw.bankstatementreader.model

enum class TransactionCategory {
    IGNORED,
    UNKNOWN,
    // Expenses
    RENT,
    POWER,
    WATER,
    INTERNET,
    HOUSE_PURCHASE,
    RECURRING_BILLS,
    EATING_OUT,
    WORK_LUNCH,
    LOAN,
    TRAVEL,
    CLOTHES,
    WORK_OUT,
    GIFTS,
    VISA,
    INVESTMENT,
    // Incomes
    SALARY,
    INTEREST,
    SELLING_STUFF,
    REFUND,
}
