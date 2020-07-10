package com.schweitzering.domain.debts

import androidx.lifecycle.LiveData
import com.schweitzering.domain.BaseRepository

interface DebtsRepository: BaseRepository<Debt> {

    override fun getAll(): LiveData<List<Debt>>

    override fun insert(model: Debt)

    override fun delete(model: Debt)

    override fun update(model: Debt)
}