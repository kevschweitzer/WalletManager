package com.schweitzering.domain.balance

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.schweitzering.domain.accounts.AccountRepository
import com.schweitzering.domain.transaction.TransactionType
import com.schweitzering.domain.transaction.TransactionsRepository

//Total balance represents all the transactions balance, including saving and investments
class GetTotalBalanceUseCase(private val accountRepository: AccountRepository) {

    fun execute(): LiveData<Float> = Transformations.map(accountRepository.getAll()) {
        it.map { it.balance }.sum()
    }
}