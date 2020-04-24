package com.schweitzering.domain.fixedExpenses.generator

import androidx.lifecycle.LiveData

/*
    Generator of Fixed Expenses.
    Aim to register the wanted Fixed expenses and generate a Fixed Expense Entity for every scheduled period
 */
interface FixedExpensesGeneratorRepository {

    fun insert(generator: FixedExpenseGenerator)
    fun delete(generator: FixedExpenseGenerator)
    fun getAll(): LiveData<List<FixedExpenseGenerator>>
}