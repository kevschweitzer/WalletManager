package com.schweitzering.domain.transaction

class AddTransactionUseCase(private val repository: TransactionsRepository) {

    fun execute(transaction: Transaction) = repository.add(transaction)
}