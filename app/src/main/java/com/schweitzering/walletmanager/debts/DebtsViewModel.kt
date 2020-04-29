package com.schweitzering.walletmanager.debts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.schweitzering.domain.debts.GetAllDebtsUseCase
import com.schweitzering.walletmanager.xsupport.mappers.toDebtProfile

class DebtsViewModel(private val getAllDebtsUseCase: GetAllDebtsUseCase) {

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
}