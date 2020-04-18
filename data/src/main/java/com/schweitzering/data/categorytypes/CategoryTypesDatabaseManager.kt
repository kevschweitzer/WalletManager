package com.schweitzering.data.categorytypes

import com.schweitzering.data.database.AppDatabase
import com.schweitzering.domain.transaction.TransactionCategory
import kotlinx.coroutines.runBlocking

class CategoryTypesDatabaseManager(private val database: AppDatabase) {

    private val categoryTypesDao = database.categoryTypesDao()

    fun add(category: TransactionCategory, type: String) {
        runBlocking {
            categoryTypesDao.insert(CategoryTypeEntity(category = category, type = type))
        }
    }

    fun remove(category: TransactionCategory, type: String) {
        runBlocking {
            categoryTypesDao.delete(CategoryTypeEntity(category = category, type = type))
        }
    }

    fun getAllByCategory(category: TransactionCategory) = categoryTypesDao.getAllByCategory(category)
}