package com.schweitzering.domain.balance

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.schweitzering.domain.accounts.AccountRepository

//Total balance represents all the transactions balance, including saving and investments
class GetTotalBalanceUseCase(private val accountRepository: AccountRepository) {

    fun execute(): LiveData<Float> = Transformations.map(accountRepository.getAll()) {
        it.map { it.balance }.sum()
    }
}