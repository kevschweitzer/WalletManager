package com.schweitzering.data.accounts

import androidx.lifecycle.Transformations
import com.schweitzering.data.xsupport.database.AppDatabase
import com.schweitzering.data.xsupport.mappers.toAccount
import com.schweitzering.data.xsupport.mappers.toAccountEntity
import com.schweitzering.domain.accounts.Account
import com.schweitzering.domain.accounts.AccountRepository
import kotlinx.coroutines.runBlocking

class AccountRepositoryImpl(private val appDatabase: AppDatabase): AccountRepository {

    private val dao = appDatabase.accountsDao()

    override fun add(account: Account) {
        runBlocking {
            dao.insert(account.toAccountEntity())
        }
    }

    override fun remove(account: Account) {
        runBlocking {
            dao.remove(account.toAccountEntity())
        }
    }

    override fun getAll() = Transformations.map(dao.getAll()) {
        it.map { it.toAccount() }
    }

    override fun update(account: Account) {
        runBlocking {
            dao.update(account.toAccountEntity())
        }
    }
}