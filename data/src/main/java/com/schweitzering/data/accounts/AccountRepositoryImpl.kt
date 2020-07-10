package com.schweitzering.data.accounts

import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.liveData
import com.schweitzering.data.xsupport.database.AppDatabase
import com.schweitzering.data.xsupport.mappers.toAccount
import com.schweitzering.data.xsupport.mappers.toAccountEntity
import com.schweitzering.domain.ActionResponse
import com.schweitzering.domain.accounts.Account
import com.schweitzering.domain.accounts.AccountRepository
import kotlinx.coroutines.runBlocking

class AccountRepositoryImpl(private val appDatabase: AppDatabase): AccountRepository {

    private val dao = appDatabase.accountsDao()

    override fun insert(model: Account) {
        runBlocking {
            dao.insert(model.toAccountEntity())
        }
    }

    override fun delete(model: Account): LiveData<ActionResponse> {
        return liveData {
            try {
                dao.remove(model.toAccountEntity())
                emit(ActionResponse.Correct)
            } catch (e: SQLiteConstraintException) {
                e.printStackTrace()
                emit(ActionResponse.CannotDeleteError)
            }
        }
    }

    override fun getAll() = Transformations.map(dao.getAll()) {
        it.map { it.toAccount() }
    }

    override fun update(model: Account) {
        runBlocking {
            dao.update(model.toAccountEntity())
        }
    }

    override fun getById(id: Int) = Transformations.map(dao.getById(id)) {
        it.toAccount()
    }

}