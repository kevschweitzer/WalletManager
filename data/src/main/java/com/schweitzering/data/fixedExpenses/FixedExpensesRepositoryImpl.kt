package com.schweitzering.data.fixedExpenses

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.schweitzering.data.xsupport.mappers.toFixedExpense
import com.schweitzering.data.xsupport.mappers.toFixedExpenseEntity
import com.schweitzering.domain.fixedExpenses.FixedExpense
import com.schweitzering.domain.fixedExpenses.FixedExpensesRepository

class FixedExpensesRepositoryImpl(private val databaseManager: FixedExpensesDatabaseManager): FixedExpensesRepository {

    override fun getFixedExpenses() = Transformations.map(databaseManager.getAll()) {
        it.map { it.toFixedExpense() }
    }

    override fun removeFixedExpense(expense: FixedExpense) {
        databaseManager.remove(expense.toFixedExpenseEntity())
    }

    override fun addFixedExpense(expense: FixedExpense) {
        databaseManager.add(expense.toFixedExpenseEntity())
    }

    override fun updateFixedExpense(expense: FixedExpense) {
        databaseManager.update(expense.toFixedExpenseEntity())
    }
}