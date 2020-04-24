package com.schweitzering.walletmanager.fixedExpenses

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.schweitzering.domain.fixedExpenses.GetFixedExpensesByPaymentUseCase
import com.schweitzering.domain.fixedExpenses.GetFixedExpensesUseCase
import com.schweitzering.domain.fixedExpenses.PayFixedExpenseUseCase
import com.schweitzering.walletmanager.xsupport.mappers.toFixedExpense
import com.schweitzering.walletmanager.xsupport.mappers.toFixedExpenseProfile

class FixedExpensesViewModel(private val getFixedExpensesUseCase: GetFixedExpensesUseCase,
                             private val payFixedExpenseUseCase: PayFixedExpenseUseCase) {

    sealed class FlowState {
        object NewExepenseClicked: FlowState()
        object PaidFixedExpensesClicked: FlowState()
    }

    //Exposed
    val state = MutableLiveData<FlowState>()
    val fixedExpenses = Transformations.map(getFixedExpensesUseCase.execute()) {
        it.sortedBy { it.isAlreadyPaid }.map { it.toFixedExpenseProfile() }
    }

    fun onNewFixedExpenseClicked() {
        state.value = FlowState.NewExepenseClicked
    }

    fun payFixedExpense(fixedExpense: FixedExpenseProfile) {
        payFixedExpenseUseCase.execute(fixedExpense.toFixedExpense())
    }

    fun onAlreadyPaidClicked() {
        state.value = FlowState.PaidFixedExpensesClicked
    }
}