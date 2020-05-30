package com.schweitzering.domain.balance

import androidx.lifecycle.Transformations
import com.schweitzering.domain.transaction.TransactionType
import com.schweitzering.domain.transaction.TransactionsRepository

/*
    Partial balance is the one that represents the current available to use money. It is
    different from total balance in the way that total balance includes savings and investments
 */
class GetPartialBalanceUseCase(private val transactionsRepository: TransactionsRepository) {

    fun execute() = Transformations.map(transactionsRepository.getAll()) {
        var balance = 0f
        it.forEach {transaction ->
            when(transaction.type) {
                TransactionType.INCOME -> balance += transaction.value
                TransactionType.EXPENSE,
                TransactionType.SAVING,
                TransactionType.INVESTMENT -> balance -= transaction.value
            }
        }
        balance
    }

}