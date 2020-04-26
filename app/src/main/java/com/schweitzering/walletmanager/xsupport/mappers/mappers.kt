package com.schweitzering.walletmanager.xsupport.mappers

import com.schweitzering.domain.fixedExpenses.FixedExpense
import com.schweitzering.domain.fixedExpenses.generator.FixedExpenseGenerator
import com.schweitzering.domain.transaction.Transaction
import com.schweitzering.walletmanager.fixedExpenses.FixedExpenseProfile
import com.schweitzering.walletmanager.fixedExpenses.generator.FixedExpenseGeneratorProfile
import com.schweitzering.walletmanager.transaction.TransactionProfile

fun TransactionProfile.toTransaction() = Transaction(value = value, date = date, category = category, categoryType = categoryType)
fun Transaction.toTransactionProfile() = TransactionProfile(value = value, date = date, category = category, categoryType = categoryType)

fun FixedExpenseProfile.toFixedExpense() = FixedExpense(id, value, category, categoryType, isAlreadyPaid, creationDate, paymentDate)
fun FixedExpense.toFixedExpenseProfile() = FixedExpenseProfile(id, value, category, categoryType, isAlreadyPaid, creationDate, paymentDate)

fun FixedExpenseGeneratorProfile.toFixedExpenseGenerator() = FixedExpenseGenerator(id, value, categoryType, creationDate, schedule)
fun FixedExpenseGenerator.toFixedExpenseGeneratorProfile() = FixedExpenseGeneratorProfile(id, value, categoryType, creationDate, schedule)