package com.schweitzering.domain.transaction

import androidx.lifecycle.LiveData
import java.time.LocalDate

interface TransactionsRepository {

    fun add(entity: Transaction)

    fun remove(entity: Transaction)

    fun getAll(): LiveData<List<Transaction>>

    fun getBetween(initialDate: LocalDate, finalDate: LocalDate): LiveData<List<Transaction>>

    fun getByCategory(category: TransactionCategory): LiveData<List<Transaction>>

    fun getByCategoryType(type: String): LiveData<List<Transaction>>
}