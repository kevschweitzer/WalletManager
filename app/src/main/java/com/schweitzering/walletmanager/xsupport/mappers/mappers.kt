package com.schweitzering.walletmanager.xsupport.mappers

import com.schweitzering.domain.fixedExpenses.FixedExpense
import com.schweitzering.domain.fixedExpenses.Period
import com.schweitzering.domain.transaction.Transaction
import com.schweitzering.walletmanager.fixedExpenses.FixedExpenseProfile
import com.schweitzering.walletmanager.fixedExpenses.PeriodProfile
import com.schweitzering.walletmanager.transaction.TransactionProfile

fun TransactionProfile.toTransaction() = Transaction(value = value, date = date, category = category, categoryType = categoryType)

fun Transaction.toTransactionProfile() = TransactionProfile(value = value, date = date, category = category, categoryType = categoryType)