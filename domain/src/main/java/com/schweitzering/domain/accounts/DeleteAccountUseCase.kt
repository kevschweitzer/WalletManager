package com.schweitzering.domain.accounts

class DeleteAccountUseCase(private val repository: AccountRepository) {

    fun execute(account: Account) = repository.remove(account)
}