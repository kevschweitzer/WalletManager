package com.schweitzering.domain.transaction

import androidx.lifecycle.LiveData
import com.schweitzering.domain.BaseRepository
import java.sql.Timestamp

interface TransactionsRepository: BaseRepository<Transaction> {

    override fun insert (model: Transaction)

    override fun delete(model: Transaction)

    override fun update(model: Transaction)

    override fun getAll(): LiveData<List<Transaction>>

    fun getBetween(initialDate: Timestamp, finalDate: Timestamp): LiveData<List<Transaction>>

    fun getByAccount(accountId: Int): LiveData<List<Transaction>>
}