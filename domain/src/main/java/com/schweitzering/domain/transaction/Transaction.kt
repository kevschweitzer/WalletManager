package com.schweitzering.domain.transaction

import java.sql.Timestamp

data class Transaction(
    var id: Int? = null,
    var value: Float,
    var date: Timestamp? = null,
    var description: String,
    var category: TransactionCategory = TransactionCategory.EXPENSE, //Each Transaction has a category
    var categoryType: String //Each category has an associated type. For example Expense can be food, house, clothes, etc.
)

enum class TransactionCategory {
    INCOME, EXPENSE, SAVING, INVESTMENT
}