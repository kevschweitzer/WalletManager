package com.schweitzering.domain.fixedExpenses

import com.schweitzering.domain.transaction.TransactionsRepository
import java.sql.Timestamp

/*
    Paying a fixed expense will set the expense as paid for that period and add a new transaction
    for the expense
 */
class PayFixedExpenseUseCase(private val fixedExpenseRepository: FixedExpensesRepository,
                             private val transactionsRepository: TransactionsRepository ) {

    fun execute(fixedExpense: FixedExpense) {
        fixedExpense.isAlreadyPaid = true
        fixedExpenseRepository.updateFixedExpense(fixedExpense)
        fixedExpense.expense.date = Timestamp(System.currentTimeMillis())
        transactionsRepository.add(fixedExpense.expense)
    }
}