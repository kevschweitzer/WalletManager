package com.schweitzering.domain.fixedExpenses.generator

import io.reactivex.Single

/*
    Generator of Fixed Expenses.
    Registers the wanted Fixed expenses and generate a Fixed Expense Entity for every scheduled period
 */
interface FixedExpensesGeneratorRepository {

    fun insert(generator: FixedExpenseGenerator)
    fun delete(generator: FixedExpenseGenerator)
    fun getAll(): Single<List<FixedExpenseGenerator>>
}