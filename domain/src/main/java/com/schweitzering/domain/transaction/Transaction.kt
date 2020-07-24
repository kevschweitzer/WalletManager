package com.schweitzering.domain.transaction

import com.schweitzering.domain.accounts.Account
import com.schweitzering.domain.categories.TransactionCategory
import com.schweitzering.domain.movements.Movement
import java.sql.Timestamp

class Transaction(
    var id: Int = 0,
    value: Float,
    date: Timestamp? = null,
    description: String,
    var type: TransactionType = TransactionType.EXPENSE,
    var category: TransactionCategory,
    var account: Account
): Movement(value, description, date)

enum class TransactionType {
    INCOME, EXPENSE
}