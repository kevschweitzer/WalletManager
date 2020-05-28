package com.schweitzering.walletmanager.fixedExpenses.generator.list

import com.schweitzering.domain.fixedExpenses.generator.GetAllFixedExpensesGeneratorsUseCase
import com.schweitzering.walletmanager.xsupport.mappers.toFixedExpenseGeneratorProfile
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FixedExpensesGeneratorsViewModel(private val getAllFixedExpensesGeneratorsUseCase: GetAllFixedExpensesGeneratorsUseCase) {

    val fixedExpenseGeneratos = getAllFixedExpensesGeneratorsUseCase.execute().map {
        it.map { it.toFixedExpenseGeneratorProfile() }
    }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}