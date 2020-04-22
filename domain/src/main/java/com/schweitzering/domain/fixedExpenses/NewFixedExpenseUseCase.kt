package com.schweitzering.domain.fixedExpenses

class NewFixedExpenseUseCase(private val repository: FixedExpensesRepository) {

    fun execute(expense: FixedExpense) {
        repository.addFixedExpense(expense)
    }
}