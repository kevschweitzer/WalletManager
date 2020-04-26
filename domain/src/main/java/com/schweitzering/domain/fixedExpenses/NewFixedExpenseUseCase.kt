package com.schweitzering.domain.fixedExpenses

class NewFixedExpenseUseCase(private val fixedExpenseRepository: FixedExpensesRepository) {

    fun execute(expense: FixedExpense) {
        fixedExpenseRepository.addFixedExpense(expense)
    }
}