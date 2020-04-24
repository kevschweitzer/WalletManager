package com.schweitzering.data.fixedExpenses.generator

import androidx.lifecycle.Transformations
import com.schweitzering.data.xsupport.mappers.toFixedExpenseGenerator
import com.schweitzering.data.xsupport.mappers.toFixedExpenseGeneratorEntity
import com.schweitzering.domain.fixedExpenses.generator.FixedExpenseGenerator
import com.schweitzering.domain.fixedExpenses.generator.FixedExpensesGeneratorRepository

class FixedExpensesGeneratorRepositoryImpl(private val databaseManager: FixedExpensesGeneratorDatabaseManager): FixedExpensesGeneratorRepository {

    override fun insert(generator: FixedExpenseGenerator) {
       databaseManager.insert(generator.toFixedExpenseGeneratorEntity())
    }

    override fun delete(generator: FixedExpenseGenerator) {
        databaseManager.delete(generator.toFixedExpenseGeneratorEntity())
    }

    override fun getAll() = Transformations.map(databaseManager.getAll()) {
        it.map { it.toFixedExpenseGenerator() }
    }
}