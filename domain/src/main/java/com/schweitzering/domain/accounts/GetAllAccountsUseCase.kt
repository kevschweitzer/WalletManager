package com.schweitzering.domain.accounts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import com.schweitzering.domain.transaction.TransactionsRepository

class GetAllAccountsUseCase(private val accountRepository: AccountRepository,
                            private val transactionsRepository: TransactionsRepository) {

    fun execute(): LiveData<List<Account>> {
        return Transformations.switchMap(accountRepository.getAll()) { accountsList ->
            val mediator = MediatorLiveData<List<Account>>()
            accountsList.forEach { account ->
                mediator.addSource(transactionsRepository.getByAccount(account.id)) {
                    account.transactions.addAll(it)
                    mediator.postValue(accountsList)
                }
            }
            mediator
        }
    }
}