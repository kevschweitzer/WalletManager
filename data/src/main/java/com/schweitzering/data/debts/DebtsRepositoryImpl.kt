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

    override fun insert(debt: Debt) {
        databaseManager.insert(debt.toDebtEntity())
    }

    override fun delete(debt: Debt) {
        databaseManager.delete(debt.toDebtEntity())
    }

    override fun update(debt: Debt) {
        databaseManager.update(debt.toDebtEntity())
    }
}