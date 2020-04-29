package com.schweitzering.domain.fixedExpenses

import com.schweitzering.domain.transaction.Transaction
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
        transactionsRepository.add(Transaction(fixedExpense.value,
            Timestamp(System.currentTimeMillis()),
            fixedExpense.category,
            fixedExpense.categoryType))
    }
}