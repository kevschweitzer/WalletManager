package com.schweitzering.domain.debts

import androidx.lifecycle.LiveData

interface DebtsRepository {

    fun getAll(): LiveData<List<Debt>>

    fun insert(debt: Debt)

    fun delete(debt: Debt)

    fun update(debt: Debt)
}