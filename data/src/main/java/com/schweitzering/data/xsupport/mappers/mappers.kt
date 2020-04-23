package com.schweitzering.data.xsupport.mappers

import com.schweitzering.data.categorytypes.CategoryTypeEntity
import com.schweitzering.data.fixedExpenses.FixedExpenseEntity
import com.schweitzering.data.transaction.TransactionEntity
import com.schweitzering.domain.categories.CategoryType
import com.schweitzering.domain.fixedExpenses.FixedExpense
import com.schweitzering.domain.transaction.Transaction

fun Transaction.toTransactionEntity() = TransactionEntity(
    value = value,
    date = date,
    category = category,
    categoryType = categoryType
)
fun TransactionEntity.toTransaction() = Transaction(value = value, date = date, category = category, categoryType = categoryType)

fun CategoryType.toCategoryTypeEntity() = CategoryTypeEntity(category = category, type = type)
fun CategoryTypeEntity.toCategoryType() = CategoryType(category = category, type = type)

fun FixedExpenseEntity.toFixedExpense() = FixedExpense(Transaction(value, date, category, categoryType), isAlreadyPaid, creationDate, schedule)

fun FixedExpense.toFixedExpenseEntity() = FixedExpenseEntity(value = expense.value, category = expense.category, categoryType = expense.categoryType,
    date = expense.date, schedule = schedule, isAlreadyPaid = isAlreadyPaid, creationDate = creationDate)