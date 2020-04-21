package com.schweitzering.data.fixedExpenses

import androidx.lifecycle.LiveData
import com.schweitzering.domain.fixedExpenses.FixedExpense
import com.schweitzering.domain.fixedExpenses.FixedExpensesRepository

class FixedExpensesRepositoryImpl: FixedExpensesRepository {

    override fun getFixedExpenses(): LiveData<List<FixedExpense>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeFixedExpense(expense: FixedExpense) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addFixedExpense(expense: FixedExpense) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}