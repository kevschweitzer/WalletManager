package com.schweitzering.domain.transaction

import java.sql.Timestamp

data class Transaction(
    var id: Int? = null,
    var value: Float,
    var date: Timestamp? = null,
    var description: String,
    var type: TransactionType = TransactionType.EXPENSE,
    var categoryId: Int
)

enum class TransactionType {
    INCOME, EXPENSE, SAVING, INVESTMENT
}