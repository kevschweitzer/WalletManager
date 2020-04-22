package com.schweitzering.walletmanager.fixedExpenses

import androidx.lifecycle.MutableLiveData

class FixedExpensesViewModel {

    sealed class FlowState {
        object NewExepenseClicked: FlowState()
    }

    //Exposed
    val state = MutableLiveData<FlowState>()

    fun onNewFixedExpenseClicked() {
        state.value = FlowState.NewExepenseClicked
    }
}