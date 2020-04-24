package com.schweitzering.data.xsupport.mappers

import com.schweitzering.data.categorytypes.CategoryTypeEntity
import com.schweitzering.data.fixedExpenses.FixedExpenseEntity
import com.schweitzering.data.fixedExpenses.generator.FixedExpenseGeneratorEntity
import com.schweitzering.data.transaction.TransactionEntity
import com.schweitzering.domain.categories.CategoryType
import com.schweitzering.domain.fixedExpenses.FixedExpense
import com.schweitzering.domain.fixedExpenses.generator.FixedExpenseGenerator
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


fun FixedExpenseEntity.toFixedExpense() = FixedExpense(id, Transaction(value, date, category, categoryType), isAlreadyPaid, creationDate, paymentDate)

fun FixedExpense.toFixedExpenseEntity() = FixedExpenseEntity(id = id, value = expense.value, category = expense.category, categoryType = expense.categoryType,
    date = expense.date, isAlreadyPaid = isAlreadyPaid, creationDate = creationDate, paymentDate = paymentDate)


fun FixedExpenseGeneratorEntity.toFixedExpenseGenerator() = FixedExpenseGenerator(id, value, categoryType, creationDate, schedule)

fun FixedExpenseGenerator.toFixedExpenseGeneratorEntity() = FixedExpenseGeneratorEntity(id, value, categoryType, creationDate, schedule)