package com.schweitzering.domain.accounts

class NewAccountUseCase(private val repository: AccountRepository) {

    fun execute(account: Account) = repository.add(account)
}