package com.schweitzering.domain.balance

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.schweitzering.domain.transaction.TransactionType
import com.schweitzering.domain.transaction.TransactionsRepository

//Total balance represents all the transactions balance, including saving and investments
class GetTotalBalanceUseCase(private val transactionsRepository: TransactionsRepository) {

    fun execute(): LiveData<Float> = Transformations.map(transactionsRepository.getAll()) {
        var balance = 0f
        it.forEach {transaction ->
            when(transaction.type) {
                TransactionType.INCOME-> balance += transaction.value
                TransactionType.EXPENSE -> balance -= transaction.value
                else -> Unit
            }
        }
        balance
    }
}