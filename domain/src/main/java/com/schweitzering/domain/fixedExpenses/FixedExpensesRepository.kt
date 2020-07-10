package com.schweitzering.domain.fixedExpenses

import androidx.lifecycle.LiveData
import com.schweitzering.domain.BaseRepository

interface FixedExpensesRepository: BaseRepository<FixedExpense> {

    override fun getAll(): LiveData<List<FixedExpense>>

    override fun delete(model: FixedExpense)

    override fun insert(model: FixedExpense)

    override fun update(model: FixedExpense)
}