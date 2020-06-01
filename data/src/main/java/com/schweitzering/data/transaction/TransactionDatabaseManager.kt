package com.schweitzering.data.transaction

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import com.schweitzering.data.xsupport.database.AppDatabase
import com.schweitzering.data.xsupport.mappers.toTransactionCategoryEntity
import com.schweitzering.data.xsupport.utils.Converters
import com.schweitzering.domain.categories.TransactionCategory
import com.schweitzering.domain.transaction.TransactionType
import kotlinx.coroutines.runBlocking
import java.sql.Timestamp
import java.time.LocalDate

class TransactionDatabaseManager(private val database: AppDatabase) {

    private val transactionsDao = database.transactionsDao()
    private val categoriesDao = database.transactionCategoriesDao()
    private val converters = Converters()

    fun insert(entity: TransactionEntity) {
        runBlocking {
            transactionsDao.insert(entity)
        }
    }

    fun getAll() = Transformations.switchMap(transactionsDao.getAll()) { transactionsList ->
        val mediator = MediatorLiveData<List<TransactionEntity>>()
        transactionsList.forEach { transaction ->
            mediator.addSource(categoriesDao.getById(transaction.categoryId)) {
                transaction.category = it
                mediator.postValue(transactionsList)
            }
        }
        mediator
    }

    fun delete(entity: TransactionEntity) {
        runBlocking {
            transactionsDao.delete(entity)
        }
    }

    /*fun getByCategory(category: TransactionCategory) = transactionsDao.getByCategory(converters.fromTransactionCategory(category))

    fun getByType(type: TransactionType) = transactionsDao.getByType(converters.fromTransactionType(type))*/

    fun getBetween(initialDate: LocalDate, finalDate: LocalDate) =
        transactionsDao.getBetween(converters.fromTimestamp(Timestamp.valueOf(initialDate.startOfDay())),
            converters.fromTimestamp(Timestamp.valueOf(finalDate.endOfDay())))
}

fun LocalDate.startOfDay(): String {
    return "${this} 00:00:00.0"
}

fun LocalDate.endOfDay(): String {
    return "${this} 23:59:59.0"
}