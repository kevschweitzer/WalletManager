package com.schweitzering.domain.finance

class getAllTransactionsUseCase(private val repository: FinanceRepository) {

    fun execute() = repository.getAll()
}