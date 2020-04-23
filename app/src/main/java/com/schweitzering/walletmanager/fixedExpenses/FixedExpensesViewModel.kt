package com.schweitzering.walletmanager.fixedExpenses

import androidx.lifecycle.MutableLiveData
import com.schweitzering.data.fixedExpenses.GetFixedExpensesUseCase

class FixedExpensesViewModel(private val getFixedExpensesUseCase: GetFixedExpensesUseCase) {

    sealed class FlowState {
        object NewExepenseClicked: FlowState()
    }

    //Exposed
    val state = MutableLiveData<FlowState>()
    val fixedExpenses = getFixedExpensesUseCase.execute()

    fun onNewFixedExpenseClicked() {
        state.value = FlowState.NewExepenseClicked
    }
}