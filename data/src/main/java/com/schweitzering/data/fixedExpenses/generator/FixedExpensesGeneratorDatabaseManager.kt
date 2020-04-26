package com.schweitzering.data.fixedExpenses.generator

    import com.schweitzering.data.xsupport.database.AppDatabase
import kotlinx.coroutines.runBlocking

class FixedExpensesGeneratorDatabaseManager(private val appDatabase: AppDatabase) {

    private val fixedExpensesGeneratorDao = appDatabase.fixedExpensesGeneratorDao()

    fun insert(generatorEntity: FixedExpenseGeneratorEntity) {
        runBlocking {
            fixedExpensesGeneratorDao.insert(generatorEntity)
        }
    }

    fun delete(generatorEntity: FixedExpenseGeneratorEntity) {
        runBlocking {
            fixedExpensesGeneratorDao.delete(generatorEntity)
        }
    }

    fun getAll() = fixedExpensesGeneratorDao.getAll()
}