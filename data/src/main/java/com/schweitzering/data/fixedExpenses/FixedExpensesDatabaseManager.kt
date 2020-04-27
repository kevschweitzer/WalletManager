package com.schweitzering.data.fixedExpenses

import com.schweitzering.data.xsupport.database.AppDatabase
import kotlinx.coroutines.runBlocking

class FixedExpensesDatabaseManager(private val appDatabase: AppDatabase) {

    private val fixedExpensesDao = appDatabase.fixedExpensesDao()

    fun add(expense: FixedExpenseEntity){
        runBlocking {
            fixedExpensesDao.insert(expense)
        }
    }

    fun remove(expense: FixedExpenseEntity){
        runBlocking {
            fixedExpensesDao.delete(expense)
        }
    }

    fun getAll() = fixedExpensesDao.getAll()

    fun update(expense: FixedExpenseEntity) {
        runBlocking {
            fixedExpensesDao.update(expense)
        }
    }
}