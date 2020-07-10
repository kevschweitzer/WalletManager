package com.schweitzering.data.debts

import androidx.lifecycle.Transformations
import com.schweitzering.data.xsupport.mappers.toDebt
import com.schweitzering.data.xsupport.mappers.toDebtEntity
import com.schweitzering.domain.debts.Debt
import com.schweitzering.domain.debts.DebtsRepository

class DebtsRepositoryImpl(private val databaseManager: DebtsDatabaseManager): DebtsRepository {

    override fun getAll() =  Transformations.map(databaseManager.getAll()) {
        it.map { it.toDebt() }
    }

    override fun insert(model: Debt) {
        databaseManager.insert(model.toDebtEntity())
    }

    override fun delete(model: Debt) {
        databaseManager.delete(model.toDebtEntity())
    }

    override fun update(model: Debt) {
        databaseManager.update(model.toDebtEntity())
    }
}