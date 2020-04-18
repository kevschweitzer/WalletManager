package com.schweitzering.walletmanager.main

import androidx.lifecycle.MutableLiveData


class MainViewModel {

    sealed class FlowState {
        object NewExpense: FlowState()
        object NewIncome: FlowState()
        object NewSaving: FlowState()
        object NewInvestment: FlowState()
    }

    //Exposed data
    val state = MutableLiveData<FlowState>()

    fun onNewExpenseClicked() {
        state.value = FlowState.NewExpense
    }

    fun onNewIncomeClicked() {
        state.value = FlowState.NewIncome
    }
}