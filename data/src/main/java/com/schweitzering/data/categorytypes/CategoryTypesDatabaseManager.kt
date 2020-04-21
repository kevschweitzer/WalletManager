package com.schweitzering.data.categorytypes

import com.schweitzering.data.xsupport.database.AppDatabase
import com.schweitzering.domain.transaction.TransactionCategory
import kotlinx.coroutines.runBlocking

class CategoryTypesDatabaseManager(private val database: AppDatabase) {

    private val categoryTypesDao = database.categoryTypesDao()

    fun add(categoryType: CategoryTypeEntity) {
        runBlocking {
            categoryTypesDao.insert(categoryType)
        }
    }

    fun remove(categoryType: CategoryTypeEntity) {
        runBlocking {
            categoryTypesDao.delete(categoryType)
        }
    }

    fun getAllByCategory(category: TransactionCategory) = categoryTypesDao.getAllByCategory(category)
}