package com.schweitzering.data.accounts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.schweitzering.data.xsupport.database.AppDatabase
import com.schweitzering.data.xsupport.mappers.toAccount
import com.schweitzering.data.xsupport.mappers.toAccountEntity
import com.schweitzering.domain.ActionResponse
import com.schweitzering.domain.accounts.Account
import com.schweitzering.domain.accounts.AccountRepository
import kotlinx.coroutines.runBlocking
import java.sql.SQLException

class AccountRepositoryImpl(private val appDatabase: AppDatabase): AccountRepository {

    private val dao = appDatabase.accountsDao()

    override fun add(account: Account) {
        runBlocking {
            dao.insert(account.toAccountEntity())
        }
    }

    override fun remove(account: Account): LiveData<ActionResponse> {
        val response = MutableLiveData<ActionResponse>()
        runBlocking {
            response.postValue(try {
                dao.remove(account.toAccountEntity())
                ActionResponse.Correct
            } catch (e: SQLException) {
                e.printStackTrace()
                ActionResponse.CannotDeleteError
            })
        }
        return response
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