package com.schweitzering.walletmanager.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.schweitzering.domain.balance.GetPartialBalanceUseCase
import com.schweitzering.domain.transaction.GetAllTransactionsUseCase
import com.schweitzering.walletmanager.mappers.toTransactionProfile


class MainViewModel(private val getAllTransactionsUseCase: GetAllTransactionsUseCase,
                    private val getPartialBalanceUseCase: GetPartialBalanceUseCase) {

    sealed class FlowState {
        object NewExpense: FlowState()
        object NewIncome: FlowState()
        object NewSaving: FlowState()
        object NewInvestment: FlowState()
    }

    //Exposed data
    val state = MutableLiveData<FlowState>()
    val lastTransactions = Transformations.map(getAllTransactionsUseCase.execute()) {
        it.map { it.toTransactionProfile() }.reversed()
    }
    val partialBalance = getPartialBalanceUseCase.execute()

    fun onNewExpenseClicked() {
        state.value = FlowState.NewExpense
    }

    fun onNewIncomeClicked() {
        state.value = FlowState.NewIncome
    }
}