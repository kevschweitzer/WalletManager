package com.schweitzering.domain.transaction

class GetAllTransactionsUseCase(private val repository: TransactionsRepository) {

    fun execute() = repository.getAll()
}