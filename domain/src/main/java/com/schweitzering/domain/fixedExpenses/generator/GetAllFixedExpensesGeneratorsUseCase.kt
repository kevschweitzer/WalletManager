package com.schweitzering.domain.fixedExpenses.generator

class GetAllFixedExpensesGeneratorsUseCase(private val repository: FixedExpensesGeneratorRepository) {

    fun execute() = repository.getAll()
}