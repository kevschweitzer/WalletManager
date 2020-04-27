package com.schweitzering.walletmanager.fixedExpenses.worker

import com.schweitzering.domain.fixedExpenses.CreateFixedExpensesForPeriodUseCase

class FixedExpensesWorkerViewModel(private val createFixedExpensesForPeriodUseCase: CreateFixedExpensesForPeriodUseCase) {

    fun createFixedExpensesForPeriod() = createFixedExpensesForPeriodUseCase.execute()

}