package com.schweitzering.data.xsupport.mappers

import com.schweitzering.data.categorytypes.CategoryTypeEntity
import com.schweitzering.data.debts.DebtEntity
import com.schweitzering.data.fixedExpenses.FixedExpenseEntity
import com.schweitzering.data.fixedExpenses.generator.FixedExpenseGeneratorEntity
import com.schweitzering.data.transaction.TransactionEntity
import com.schweitzering.domain.categories.CategoryType
import com.schweitzering.domain.debts.Debt
import com.schweitzering.domain.fixedExpenses.FixedExpense
import com.schweitzering.domain.fixedExpenses.generator.FixedExpenseGenerator
import com.schweitzering.domain.transaction.Transaction

fun Transaction.toTransactionEntity() = TransactionEntity(
    value = value,
    date = date, //Won't be null when transaction is created
    description = description,
    category = category,
    categoryType = categoryType
)
fun TransactionEntity.toTransaction() = Transaction(value = value, date = date, description = description, category = category, categoryType = categoryType)


fun CategoryType.toCategoryTypeEntity() = CategoryTypeEntity(category = category, type = type)
fun CategoryTypeEntity.toCategoryType() = CategoryType(category = category, type = type)


fun FixedExpenseEntity.toFixedExpense() = FixedExpense(id, expense, isAlreadyPaid, creationDate, paymentDate)

fun FixedExpense.toFixedExpenseEntity() = FixedExpenseEntity(id, expense, isAlreadyPaid, creationDate, paymentDate)


fun FixedExpenseGeneratorEntity.toFixedExpenseGenerator() = FixedExpenseGenerator(id, expense, creationDate, schedule)
fun FixedExpenseGenerator.toFixedExpenseGeneratorEntity() = FixedExpenseGeneratorEntity(id ?: 0, expense, creationDate, schedule)

fun Debt.toDebtEntity() = DebtEntity(id, transaction, creationDate, isResolved, resolveDate)
fun DebtEntity.toDebt() = Debt(id, transaction, creationDate, isResolved, resolveDate)