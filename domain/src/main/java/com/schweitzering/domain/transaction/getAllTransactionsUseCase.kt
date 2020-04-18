package com.schweitzering.domain.transaction

class getAllTransactionsUseCase(private val repository: TransactionsRepository) {

    fun execute() = repository.getAll()
}