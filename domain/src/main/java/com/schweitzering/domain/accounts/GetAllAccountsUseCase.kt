package com.schweitzering.domain.accounts

class GetAllAccountsUseCase(private val repository: AccountRepository) {

    fun execute() = repository.getAll()
}