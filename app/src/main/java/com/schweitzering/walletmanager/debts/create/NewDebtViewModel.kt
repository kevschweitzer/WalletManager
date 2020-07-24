package com.schweitzering.walletmanager.debts.create

import androidx.lifecycle.MutableLiveData
import com.schweitzering.domain.accounts.Account
import com.schweitzering.domain.categories.TransactionCategory
import com.schweitzering.domain.debts.NewDebtUseCase
import com.schweitzering.domain.transaction.Transaction
import com.schweitzering.domain.transaction.TransactionType
import com.schweitzering.walletmanager.debts.DebtProfile
import com.schweitzering.walletmanager.xsupport.mappers.toDebt
import java.sql.Timestamp

class NewDebtViewModel(private val newDebtUseCase: NewDebtUseCase) {

    sealed class FlowState {
        object SuccessCreation : FlowState()
    }

    val state = MutableLiveData<FlowState>()

    fun onCreateDebtClicked() {
        newDebtUseCase.execute(DebtProfile(transaction = Transaction(
            value = 40f,
            description = "",
            type = TransactionType.INCOME,
            category = TransactionCategory(),
            account = Account()
        ),
            creationDate = Timestamp(System.currentTimeMillis())).toDebt())
        state.value = FlowState.SuccessCreation
    }
}