package com.schweitzering.data.categories

import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.schweitzering.data.xsupport.database.AppDatabase
import com.schweitzering.domain.ActionResponse
import com.schweitzering.domain.transaction.TransactionType
import kotlinx.coroutines.runBlocking

class TransactionCategoryDatabaseManager(private val database: AppDatabase) {

    private val transactionCategoriesDao = database.transactionCategoriesDao()

    fun add(category: TransactionCategoryEntity) {
        runBlocking {
            transactionCategoriesDao.insert(category)
        }
    }

    fun remove(category: TransactionCategoryEntity): LiveData<ActionResponse> {
        val response = MutableLiveData<ActionResponse>()
        runBlocking {
            response.postValue(try {
                transactionCategoriesDao.delete(category)
                ActionResponse.Correct
            } catch (e: SQLiteConstraintException) {
                ActionResponse.CannotDeleteError
            })
        }
        return response
    }

    fun getAllByType(type: TransactionType) = transactionCategoriesDao.getAllByType(type)

    fun getAll() = transactionCategoriesDao.getAll()
}