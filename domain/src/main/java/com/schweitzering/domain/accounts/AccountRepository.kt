package com.schweitzering.domain.accounts

import androidx.lifecycle.LiveData

interface AccountRepository {

    fun add(account: Account)

    fun remove(account: Account)

    fun getAll(): LiveData<List<Account>>

    fun update(account: Account)
}