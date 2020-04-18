package com.schweitzering.domain.transaction

class addTransactionUseCase(private val repository: TransactionsRepository) {

    fun execute(entity: Transaction) = repository.add(entity)
}