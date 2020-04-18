package com.schweitzering.domain.transaction

class AddTransactionUseCase(private val repository: TransactionsRepository) {

    fun execute(entity: Transaction) = repository.add(entity)
}