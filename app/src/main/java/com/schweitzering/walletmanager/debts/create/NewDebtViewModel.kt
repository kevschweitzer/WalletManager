package com.schweitzering.walletmanager.debts.create

import androidx.lifecycle.MutableLiveData
import com.schweitzering.domain.debts.Debt
import com.schweitzering.domain.debts.NewDebtUseCase
import com.schweitzering.domain.transaction.TransactionCategory
import com.schweitzering.walletmanager.debts.DebtProfile
import com.schweitzering.walletmanager.xsupport.mappers.toDebt
import java.sql.Timestamp

class NewDebtViewModel(private val newDebtUseCase: NewDebtUseCase) {

    sealed class FlowState {
        object SuccessCreation: FlowState()
    }

    val state = MutableLiveData<FlowState>()

    fun onCreateDebtClicked() {
        newDebtUseCase.execute(DebtProfile(
            value = 40f,
            category = TransactionCategory.INCOME,
            categoryType = "Friend",
            creationDate = Timestamp(System.currentTimeMillis())
        ).toDebt())
        state.value = FlowState.SuccessCreation
    }
}