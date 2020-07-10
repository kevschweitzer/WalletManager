package com.schweitzering.domain.accounts

import androidx.lifecycle.LiveData
import com.schweitzering.domain.ActionResponse
import com.schweitzering.domain.BaseRepository

interface AccountRepository: BaseRepository<Account> {

    override fun insert(model: Account)

    override fun delete(model: Account): LiveData<ActionResponse>

    override fun getAll(): LiveData<List<Account>>

    override fun update(model: Account)

    fun getById(id: Int): LiveData<Account>
}