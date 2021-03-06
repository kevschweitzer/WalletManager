package com.schweitzering.data.transaction

import com.schweitzering.data.xsupport.database.AppDatabase
import com.schweitzering.data.xsupport.utils.Converters
import kotlinx.coroutines.runBlocking
import java.sql.Timestamp
import java.time.LocalDate

class TransactionDatabaseManager(private val database: AppDatabase) {

    private val transactionsDao = database.transactionsDao()
    private val converters = Converters()

    fun insert(entity: TransactionEntity) {
        runBlocking {
            transactionsDao.insert(entity)
        }
    }

    fun getAll() = transactionsDao.getAll()

    fun delete(entity: TransactionEntity) {
        runBlocking {
            transactionsDao.delete(entity)
        }
    }

    fun update(entity: TransactionEntity) {
        runBlocking {
            transactionsDao.update(entity)
        }
    }

    fun getBetween(initialDate: Timestamp, finalDate: Timestamp) =
        transactionsDao.getBetween(converters.fromTimestamp(initialDate),
            converters.fromTimestamp(finalDate))

    fun getByAccount(accountId: Int) = transactionsDao.getByAccount(accountId)
}

fun LocalDate.startOfDay(): String {
    return "${this} 00:00:00.0"
}

fun LocalDate.endOfDay(): String {
    return "${this} 23:59:59.0"
}