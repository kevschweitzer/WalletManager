package com.schweitzering.domain.fixedExpenses.generator

class NewFixedExpenseGeneratorUseCase(private val repository: FixedExpensesGeneratorRepository) {

    fun execute(generator: FixedExpenseGenerator) = repository.insert(generator)
}