package com.schweitzering.walletmanager.xsupport.mappers

import com.schweitzering.domain.debts.Debt
import com.schweitzering.domain.fixedExpenses.FixedExpense
import com.schweitzering.domain.fixedExpenses.generator.FixedExpenseGenerator
import com.schweitzering.domain.transaction.Transaction
import com.schweitzering.walletmanager.debts.DebtProfile
import com.schweitzering.walletmanager.fixedExpenses.FixedExpenseProfile
import com.schweitzering.walletmanager.fixedExpenses.generator.FixedExpenseGeneratorProfile
import com.schweitzering.walletmanager.transaction.TransactionProfile

fun TransactionProfile.toTransaction() = Transaction(value = value,
    date = date,
    description = description,
    transactionCategory = type,
    categoryType = categoryType)

fun Transaction.toTransactionProfile() = TransactionProfile(value = value,
    date = date,
    description = description,
    type = transactionCategory,
    categoryType = categoryType)

fun FixedExpenseProfile.toFixedExpense() =
    FixedExpense(id, expense, isAlreadyPaid, creationDate, paymentDate)

fun FixedExpense.toFixedExpenseProfile() =
    FixedExpenseProfile(id, expense, isAlreadyPaid, creationDate, paymentDate)

fun FixedExpenseGeneratorProfile.toFixedExpenseGenerator() =
    FixedExpenseGenerator(id, expense, creationDate, schedule)

fun FixedExpenseGenerator.toFixedExpenseGeneratorProfile() =
    FixedExpenseGeneratorProfile(id, expense, creationDate, schedule)

fun Debt.toDebtProfile() = DebtProfile(id, transaction, creationDate, isResolved)
fun DebtProfile.toDebt() = Debt(id, transaction, creationDate, isResolved)