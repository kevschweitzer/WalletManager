package com.schweitzering.data.debts

import com.schweitzering.data.xsupport.database.AppDatabase
import kotlinx.coroutines.runBlocking

class DebtsDatabaseManager(private val appDatabase: AppDatabase) {

    private val debtsDao = appDatabase.debtsDao()

    fun insert(debt: DebtEntity){
        runBlocking {
            debtsDao.insert(debt)
        }
    }
    fun delete(debt: DebtEntity){
        runBlocking {
            debtsDao.delete(debt)
        }
    }
    fun update(debt: DebtEntity){
        runBlocking {
            debtsDao.update(debt)
        }
    }
    fun getAll() = debtsDao.getAll()
}