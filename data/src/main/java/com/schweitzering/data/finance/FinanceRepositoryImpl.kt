package com.schweitzering.data.finance

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.schweitzering.data.mappers.toFinance
import com.schweitzering.data.mappers.toFinanceEntity
import com.schweitzering.domain.finance.Finance
import com.schweitzering.domain.finance.FinanceCategory
import com.schweitzering.domain.finance.FinanceRepository
import java.sql.Timestamp
import java.time.LocalDate

class FinanceRepositoryImpl(private val databaseManager: FinanceDatabaseManager): FinanceRepository {

    override fun add(entity: Finance) {
        databaseManager.insert(entity.toFinanceEntity())
    }

    override fun remove(entity: Finance) {
        databaseManager.delete(entity.toFinanceEntity())
    }

    override fun getAll(): LiveData<List<Finance>> {
       return Transformations.map(databaseManager.getAll()) { list ->
           list.map { it.toFinance() }
       }
    }

    override fun getBetween(initialDate: LocalDate, finalDate: LocalDate): LiveData<List<Finance>> {
        return Transformations.map(databaseManager.getBetween(initialDate, finalDate)) { list ->
            list.map { it.toFinance() }
        }
    }

    override fun getByCategory(category: FinanceCategory): LiveData<List<Finance>> {
        return Transformations.map(databaseManager.getByCategory(category)) { list ->
            list.map { it.toFinance() }
        }
    }

    override fun getByFinanceCategoryType(type: String): LiveData<List<Finance>> {
        return Transformations.map(databaseManager.getByFinanceCategoryType(type)) { list ->
            list.map { it.toFinance() }
        }
    }
}