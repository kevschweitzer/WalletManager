package com.schweitzering.domain.transaction

import java.sql.Timestamp

data class Transaction(
    var value: Float,
    var date: Timestamp,
    var category: TransactionCategory, //Each Transaction has a category
    var categoryType: String //Each category has an associated type. For example Expense can be food, house, clothes, etc.
)

enum class TransactionCategory {
    INCOME, EXPENSE, SAVING, INVESTMENT
}