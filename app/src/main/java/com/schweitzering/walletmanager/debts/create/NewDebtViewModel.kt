package com.schweitzering.walletmanager.debts.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.schweitzering.domain.accounts.GetAllAccountsUseCase
import com.schweitzering.domain.categories.GetTransactionCategoriesForTypeUseCase
import com.schweitzering.domain.categories.TransactionCategory
import com.schweitzering.domain.debts.Debt
import com.schweitzering.domain.debts.NewDebtUseCase
import com.schweitzering.domain.transaction.TransactionType
import com.schweitzering.walletmanager.commons.BaseTransactionViewModel
import java.sql.Timestamp

class NewDebtViewModel(
    private val newDebtUseCase: NewDebtUseCase,
    getTransactionCategoriesForTypeUseCase: GetTransactionCategoriesForTypeUseCase
): BaseTransactionViewModel(getTransactionCategoriesForTypeUseCase) {

    sealed class FlowState {
        object SuccessCreation : FlowState()
    }

    val state = MutableLiveData<FlowState>()

    init {
        transactionType = TransactionType.INCOME
    }

    override fun onContinueClicked() {
        newDebtUseCase.execute(
            Debt(
                value = if(value.isNotEmpty()) value.toFloat() else 0f,
                description = description,
                category = selectedCategory,
                creationDate = Timestamp(System.currentTimeMillis()),
                type = transactionType
            )
        )
        state.value = FlowState.SuccessCreation
    }
}