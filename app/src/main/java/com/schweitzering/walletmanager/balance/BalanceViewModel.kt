package com.schweitzering.walletmanager.balance

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.schweitzering.domain.balance.GetPartialBalanceUseCase
import com.schweitzering.domain.balance.GetTotalBalanceUseCase
import com.schweitzering.domain.transaction.GetAllTransactionsUseCase
import com.schweitzering.walletmanager.xsupport.mappers.toTransactionProfile


class BalanceViewModel(private val getAllTransactionsUseCase: GetAllTransactionsUseCase,
                       private val getPartialBalanceUseCase: GetPartialBalanceUseCase,
                       private val getTotalBalanceUseCase: GetTotalBalanceUseCase) {

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
    val totalBalance = getTotalBalanceUseCase.execute()

    fun onNewExpenseClicked() {
        state.value = FlowState.NewExpense
    }

    fun onNewIncomeClicked() {
        state.value = FlowState.NewIncome
    }

    fun onNewSavingClicked() {
        state.value = FlowState.NewSaving
    }

    fun onNewInvestmentClicked() {
        state.value = FlowState.NewInvestment
    }
}