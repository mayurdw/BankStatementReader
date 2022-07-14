package com.mayurdw.bankstatementreader.model

sealed class TransactionCategory {
    data class Income(val category: IncomeCategory) : TransactionCategory()
    data class Expense(val expenseCategory: ExpenseCategory) : TransactionCategory()
    data class Unknown(val unknown: UnknownCategory) : TransactionCategory()
    data class Ignored(val ignored: IgnoredCategory) : TransactionCategory()
}

enum class IgnoredCategory {
    IGNORED
}

enum class UnknownCategory {
    UNKNOWN
}

enum class ExpenseCategory {
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
}

enum class IncomeCategory {
    SALARY,
    INTEREST,
    SELLING_STUFF,
    REFUND,
}
