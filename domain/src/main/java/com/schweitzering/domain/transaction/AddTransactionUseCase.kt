package com.schweitzering.domain.transaction

import androidx.lifecycle.Transformations
import com.schweitzering.domain.accounts.AccountRepository

class AddTransactionUseCase(private val repository: TransactionsRepository,
                            private val accountRepository: AccountRepository) {

    fun execute(transaction: Transaction){
        repository.add(transaction)
        Transformations.map(accountRepository.getById(transaction.accountId)) {
            it.balance += transaction.value
            accountRepository.update(it)
        }
    }
}