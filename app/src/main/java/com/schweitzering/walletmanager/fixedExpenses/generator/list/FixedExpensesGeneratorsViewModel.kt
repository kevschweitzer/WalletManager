package com.schweitzering.walletmanager.fixedExpenses.generator.list

import androidx.lifecycle.Transformations
import com.schweitzering.domain.fixedExpenses.generator.GetAllFixedExpensesGeneratorsUseCase
import com.schweitzering.walletmanager.xsupport.mappers.toFixedExpenseGeneratorProfile

class FixedExpensesGeneratorsViewModel(private val getAllFixedExpensesGeneratorsUseCase: GetAllFixedExpensesGeneratorsUseCase) {

    val fixedExpenseGeneratos = Transformations.map(getAllFixedExpensesGeneratorsUseCase.execute()) {
        it.map { it.toFixedExpenseGeneratorProfile() }
    }
}