package com.schweitzering.domain.fixedExpenses

class GetFixedExpensesUseCase(private val repository: FixedExpensesRepository) {

    fun execute() = repository.getAll()
}