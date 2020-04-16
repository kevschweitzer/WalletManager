package com.schweitzering.domain.finance

import androidx.lifecycle.LiveData
import java.sql.Timestamp
import java.time.LocalDate

interface FinanceRepository {

    fun add(entity: Finance)

    fun remove(entity: Finance)

    fun getAll(): LiveData<List<Finance>>

    fun getBetween(initialDate: LocalDate, finalDate: LocalDate): LiveData<List<Finance>>

    fun getByCategory(category: FinanceCategory): LiveData<List<Finance>>

    fun getByFinanceCategoryType(type: String): LiveData<List<Finance>>
}