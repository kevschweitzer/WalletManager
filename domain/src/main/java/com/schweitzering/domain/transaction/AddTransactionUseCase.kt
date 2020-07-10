package com.schweitzering.domain.transaction

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.schweitzering.domain.ActionResponse
import com.schweitzering.domain.accounts.Account
import com.schweitzering.domain.accounts.AccountRepository

class AddTransactionUseCase(private val repository: TransactionsRepository,
                            private val accountRepository: AccountRepository) {

    fun execute(transaction: Transaction, account: Account?): LiveData<ActionResponse> {
        return liveData {
            account?.let {
                repository.insert(transaction)
                when(transaction.type) {
                    TransactionType.EXPENSE -> account.balance -= transaction.value
                    TransactionType.INCOME -> account.balance += transaction.value
                }
                accountRepository.update(account)
                emit(ActionResponse.Correct)
            } ?: emit(ActionResponse.UnknownError)
        }
    }
}