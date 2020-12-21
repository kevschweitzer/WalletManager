package com.schweitzering.walletmanager.debts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.schweitzering.domain.ActionResponse
import com.schweitzering.domain.accounts.Account
import com.schweitzering.domain.accounts.GetAllAccountsUseCase
import com.schweitzering.domain.debts.Debt
import com.schweitzering.domain.debts.GetAllDebtsUseCase
import com.schweitzering.domain.debts.ResolveDebtUseCase

class DebtsViewModel(
    private val getAllDebtsUseCase: GetAllDebtsUseCase,
    private val resolveDebtUseCase: ResolveDebtUseCase,
    private val getAllAccountsUseCase: GetAllAccountsUseCase
) {

    sealed class FlowState {
        object NewDebtClicked : FlowState()
        class ResolveDebt(var debt: Debt) : FlowState()
    }

    val debts = getAllDebtsUseCase.execute()
    val accounts = getAllAccountsUseCase.execute()
    val state = MutableLiveData<FlowState>()
    val selectedAccountId = 0
    val selectedAccount: Account?
        get() = accounts.value?.get(selectedAccountId)

    fun onNewDebtClicked() {
        state.value = FlowState.NewDebtClicked
    }

    fun resolveDebtClicked(debt: Debt) {
        state.value = FlowState.ResolveDebt(debt)
    }

    fun resolveDebt(debt: Debt, account: Account): LiveData<ActionResponse> {
        return resolveDebtUseCase.execute(debt, account)
    }
}