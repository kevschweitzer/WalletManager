package com.schweitzering.domain.accounts

import androidx.lifecycle.LiveData
import com.schweitzering.domain.ActionResponse

interface AccountRepository {

    fun add(account: Account)

    fun remove(account: Account): LiveData<ActionResponse>

    fun getAll(): LiveData<List<Account>>

    fun update(account: Account)
}