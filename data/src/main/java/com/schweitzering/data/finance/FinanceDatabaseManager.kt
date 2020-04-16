package com.schweitzering.data.finance

import com.schweitzering.data.database.AppDatabase
import com.schweitzering.data.utils.Converters
import com.schweitzering.domain.finance.FinanceCategory
import kotlinx.coroutines.runBlocking
import java.sql.Timestamp
import java.time.LocalDate
import java.time.temporal.TemporalQueries.localDate



class FinanceDatabaseManager(private val database: AppDatabase) {

    private val financeDao = database.financeDao()
    private val converters = Converters()

    fun insert(entity: FinanceEntity) {
        runBlocking {
            financeDao.insert(entity)
        }
    }

    fun getAll() = financeDao.getAll()

    fun delete(entity: FinanceEntity) {
        runBlocking {
            financeDao.delete(entity)
        }
    }

    fun getByCategory(category: FinanceCategory) = financeDao.getByCategory(converters.fromFinanceCategory(category))

    fun getByFinanceCategoryType(type: String) = financeDao.getByFinanceCategoryType(type)

    fun getBetween(initialDate: LocalDate, finalDate: LocalDate) =
        financeDao.getBetween(converters.fromTimestamp(Timestamp.valueOf(initialDate.startOfDay())),
            converters.fromTimestamp(Timestamp.valueOf(finalDate.endOfDay())))
}

fun LocalDate.startOfDay(): String {
    return "${this} 00:00:00.0"
}

fun LocalDate.endOfDay(): String {
    return "${this} 23:59:59.0"
}