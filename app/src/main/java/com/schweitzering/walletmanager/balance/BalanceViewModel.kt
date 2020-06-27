package com.schweitzering.walletmanager.balance

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.schweitzering.domain.balance.GetLastMovementsUseCase
import com.schweitzering.domain.balance.GetTotalBalanceUseCase
import com.schweitzering.domain.transaction.GetAllTransactionsUseCase
import com.schweitzering.walletmanager.xsupport.mappers.toTransactionProfile


class BalanceViewModel(private val getAllTransactionsUseCase: GetAllTransactionsUseCase,
                       private val getTotalBalanceUseCase: GetTotalBalanceUseCase,
                        private val getLastMovementsUseCase: GetLastMovementsUseCase) {

    sealed class FlowState {
        object NewExpense : FlowState()
        object NewIncome : FlowState()
        object NewTransfer: FlowState()
    }

    private val _state = MutableLiveData<FlowState>()
    val state: LiveData<FlowState> = _state
    val lastMovements = Transformations.map(getLastMovementsUseCase.execute()) {
        it.sortedByDescending { it.date }
    }
    val totalBalance = getTotalBalanceUseCase.execute()

    fun onNewExpenseClicked() {
        _state.value = FlowState.NewExpense
    }

    fun onNewIncomeClicked() {
        _state.value = FlowState.NewIncome
    }

    fun onNewTransferClicked() {
        _state.value = FlowState.NewTransfer
    }
}