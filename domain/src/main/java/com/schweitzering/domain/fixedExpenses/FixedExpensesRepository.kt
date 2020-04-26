package com.schweitzering.domain.fixedExpenses

import androidx.lifecycle.LiveData

interface FixedExpensesRepository {

    fun getFixedExpenses(): LiveData<List<FixedExpense>>

    fun removeFixedExpense(expense: FixedExpense)

    fun addFixedExpense(expense: FixedExpense)

    fun updateFixedExpense(expense: FixedExpense)
}