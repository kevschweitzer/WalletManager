package com.schweitzering.data.fixedExpenses

import androidx.lifecycle.Transformations
import com.schweitzering.data.xsupport.mappers.toFixedExpense
import com.schweitzering.data.xsupport.mappers.toFixedExpenseEntity
import com.schweitzering.domain.fixedExpenses.FixedExpense
import com.schweitzering.domain.fixedExpenses.FixedExpensesRepository

class FixedExpensesRepositoryImpl(private val databaseManager: FixedExpensesDatabaseManager): FixedExpensesRepository {

    override fun getAll() = Transformations.map(databaseManager.getAll()) {
        it.map { it.toFixedExpense() }
    }

    override fun delete(model: FixedExpense) {
        databaseManager.remove(model.toFixedExpenseEntity())
    }

    override fun insert(model: FixedExpense) {
        databaseManager.add(model.toFixedExpenseEntity())
    }

    override fun update(model: FixedExpense) {
        databaseManager.update(model.toFixedExpenseEntity())
    }
}