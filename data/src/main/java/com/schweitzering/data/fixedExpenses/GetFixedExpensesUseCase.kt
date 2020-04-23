package com.schweitzering.data.fixedExpenses

import com.schweitzering.domain.fixedExpenses.FixedExpensesRepository

class GetFixedExpensesUseCase(private val repository: FixedExpensesRepository) {

    fun execute() = repository.getFixedExpenses()
}