package com.schweitzering.domain.fixedExpenses

import com.schweitzering.domain.transaction.TransactionsRepository

/*
    Paying a fixed expense will set the expense as paid for that period and add a new transaction
    for the expense
 */
class PayFixedExpenseUseCase(private val fixedExpenseRepository: FixedExpensesRepository,
                             private val transactionsRepository: TransactionsRepository ) {

    fun execute(fixedExpense: FixedExpense) {
        fixedExpense.isAlreadyPaid = true
        fixedExpenseRepository.updateFixedExpense(fixedExpense)
        transactionsRepository.add(fixedExpense.expense)
    }
}