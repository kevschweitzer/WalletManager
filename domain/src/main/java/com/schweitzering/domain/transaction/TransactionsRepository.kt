package com.schweitzering.domain.transaction

import androidx.lifecycle.LiveData
import java.sql.Timestamp

interface TransactionsRepository {

    fun add(entity: Transaction)

    fun remove(entity: Transaction)

    fun getAll(): LiveData<List<Transaction>>

    fun getBetween(initialDate: Timestamp, finalDate: Timestamp): LiveData<List<Transaction>>

    fun getByAccount(accountId: Int): LiveData<List<Transaction>>
}