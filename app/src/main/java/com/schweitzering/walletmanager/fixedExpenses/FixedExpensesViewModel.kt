package com.schweitzering.walletmanager.fixedExpenses

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.schweitzering.domain.fixedExpenses.GetFixedExpensesByPaymentUseCase
import com.schweitzering.domain.fixedExpenses.PayFixedExpenseUseCase
import com.schweitzering.walletmanager.xsupport.mappers.toFixedExpense
import com.schweitzering.walletmanager.xsupport.mappers.toFixedExpenseProfile

class FixedExpensesViewModel(private val getFixedExpensesByPaymentUseCase: GetFixedExpensesByPaymentUseCase,
                             private val payFixedExpenseUseCase: PayFixedExpenseUseCase) {

    sealed class FlowState {
        object NewExepenseClicked: FlowState()
    }

    //Exposed
    val state = MutableLiveData<FlowState>()
    val unpaidFixedExpenses = Transformations.map(getFixedExpensesByPaymentUseCase.execute(false)) {
        it.map { it.toFixedExpenseProfile() }
    }

    fun onNewFixedExpenseClicked() {
        state.value = FlowState.NewExepenseClicked
    }

    fun payFixedExpense(fixedExpense: FixedExpenseProfile) {
        payFixedExpenseUseCase.execute(fixedExpense.toFixedExpense())
    }
}