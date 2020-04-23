package com.schweitzering.domain.fixedExpenses

import com.schweitzering.domain.fixedExpenses.FixedExpensesRepository

class GetFixedExpensesUseCase(private val repository: FixedExpensesRepository) {

    fun execute() = repository.getFixedExpenses()
}