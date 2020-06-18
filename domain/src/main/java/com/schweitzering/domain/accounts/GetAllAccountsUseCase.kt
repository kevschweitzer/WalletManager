package com.schweitzering.domain.accounts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import com.schweitzering.domain.transaction.Transaction
import com.schweitzering.domain.transaction.TransactionType
import com.schweitzering.domain.transaction.TransactionsRepository

class GetAllAccountsUseCase(private val accountRepository: AccountRepository,
                            private val transactionsRepository: TransactionsRepository) {

    fun execute(): LiveData<List<Account>> {
        return Transformations.switchMap(accountRepository.getAll()) { accountsList ->
            val mediator = MediatorLiveData<List<Account>>()
            accountsList.forEach { account ->
                mediator.addSource(transactionsRepository.getByAccount(account.id)) {
                    account.transactions.toMutableList().addAll(it)
                    account.balance = getBalance(it)
                }
            }
            mediator.postValue(accountsList)
            mediator
        }
    }

    private fun getBalance(transactions: List<Transaction>): Float {
        return (transactions.filter {
            it.type == TransactionType.INCOME || it.type == TransactionType.SAVING || it.type == TransactionType.INVESTMENT
        }
            .sumByDouble { it.value.toDouble() } + transactions.filter { it.type == TransactionType.EXPENSE }
            .sumByDouble { -it.value.toDouble() }).toFloat()
    }
}