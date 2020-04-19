package com.schweitzering.domain.balance

import androidx.lifecycle.Transformations
import com.schweitzering.domain.transaction.TransactionCategory
import com.schweitzering.domain.transaction.TransactionsRepository


/*
    Partial balance is the one that represents the current available to use money. It is
    different from total balance in the way that total balance includes savings and investments
 */
class GetPartialBalanceUseCase(private val transactionsRepository: TransactionsRepository) {

    fun execute() = Transformations.map(transactionsRepository.getAll()) {
        var balance = 0f
        it.forEach {
            when(it.category) {
                TransactionCategory.INCOME -> balance += it.value
                TransactionCategory.EXPENSE -> balance -= it.value
                else -> Unit //Do nothing
            }
        }
        balance
    }

}