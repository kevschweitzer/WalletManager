package com.schweitzering.data.mappers

import com.schweitzering.data.transaction.TransactionEntity
import com.schweitzering.domain.transaction.Transaction

fun Transaction.toTransactionEntity() = TransactionEntity(
    value = value,
    date = date,
    category = category,
    categoryType = categoryType
)

fun TransactionEntity.toTransaction() = Transaction(value = value, date = date, category = category, categoryType = categoryType)