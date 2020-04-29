package com.schweitzering.walletmanager.debts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.schweitzering.domain.debts.GetAllDebtsUseCase
import com.schweitzering.domain.debts.ResolveDebtUseCase
import com.schweitzering.walletmanager.xsupport.mappers.toDebt
import com.schweitzering.walletmanager.xsupport.mappers.toDebtProfile

class DebtsViewModel(private val getAllDebtsUseCase: GetAllDebtsUseCase,
                    private val resolveDebtUseCase: ResolveDebtUseCase) {

    sealed class FlowState {
        object NewDebtClicked: FlowState()
    }

    val debts = Transformations.map(getAllDebtsUseCase.execute()) {
        it.map { it.toDebtProfile() }
    }
    val state = MutableLiveData<FlowState>()

    fun onNewDebtClicked() {
        state.value = FlowState.NewDebtClicked
    }

    fun resolveDebt(debt: DebtProfile) {
        resolveDebtUseCase.execute(debt.toDebt())
    }
}