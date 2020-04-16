package com.schweitzering.domain.finance

class addTransactionUseCase(private val repository: FinanceRepository) {

    fun execute(entity: Finance) = repository.add(entity)
}