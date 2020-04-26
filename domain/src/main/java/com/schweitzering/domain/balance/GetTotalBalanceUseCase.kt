package com.schweitzering.domain.balance

import android.util.Log
import androidx.lifecycle.Transformations
import com.schweitzering.domain.transaction.TransactionCategory
import com.schweitzering.domain.transaction.TransactionsRepository


/*
    Total balance represents all the transactions balance, including saving and investments
 */
class GetTotalBalanceUseCase(private val transactionsRepository: TransactionsRepository) {

    fun execute() = Transformations.map(transactionsRepository.getAll()) {
        var balance = 0f
        it.forEach {
            when(it.category) {
                TransactionCategory.INCOME-> balance += it.value
                TransactionCategory.EXPENSE -> balance -= it.value
                else -> Unit //Do nothing
            }
        }
        balance
    }
}