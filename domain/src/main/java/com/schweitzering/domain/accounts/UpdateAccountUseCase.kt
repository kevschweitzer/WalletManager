package com.schweitzering.domain.accounts

class UpdateAccountUseCase(private val repository: AccountRepository) {

    fun execute(account: Account) = repository.update(account)
}