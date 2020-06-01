package com.schweitzering.data.categories

import com.schweitzering.data.xsupport.database.AppDatabase
import com.schweitzering.domain.transaction.TransactionType
import kotlinx.coroutines.runBlocking

class TransactionCategoryDatabaseManager(private val database: AppDatabase) {

    private val transactionCategoriesDao = database.transactionCategoriesDao()

    fun add(category: TransactionCategoryEntity) {
        runBlocking {
            transactionCategoriesDao.insert(category)
        }
    }

    fun remove(category: TransactionCategoryEntity) {
        runBlocking {
            transactionCategoriesDao.delete(category)
        }
    }

    fun getAllByType(type: TransactionType) = transactionCategoriesDao.getAllByType(type)

    fun getAll() = transactionCategoriesDao.getAll()
}