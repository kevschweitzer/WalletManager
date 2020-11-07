package com.schweitzering.walletmanager.transaction

import android.content.Intent
import androidx.lifecycle.*
import com.schweitzering.domain.ActionResponse
import com.schweitzering.domain.accounts.Account
import com.schweitzering.domain.accounts.GetAllAccountsUseCase
import com.schweitzering.domain.categories.GetTransactionCategoriesForTypeUseCase
import com.schweitzering.domain.categories.TransactionCategory
import com.schweitzering.domain.transaction.AddTransactionUseCase
import com.schweitzering.domain.transaction.Transaction
import com.schweitzering.domain.transaction.TransactionType
import com.schweitzering.walletmanager.commons.BaseTransactionViewModel
import com.schweitzering.walletmanager.xsupport.mappers.toTransaction
import com.schweitzering.walletmanager.xsupport.utils.Constants.Companion.TRANSACTION_CATEGORY
import java.sql.Timestamp

class TransactionViewModel(private val addTransactionUseCase: AddTransactionUseCase,
                           getTransactionCategoriesForTypeUseCase: GetTransactionCategoriesForTypeUseCase,
                           getAllAccountsUseCase: GetAllAccountsUseCase
) : BaseTransactionViewModel(
        getTransactionCategoriesForTypeUseCase,
        getAllAccountsUseCase
) {

    sealed class TransactionState {
        object ContinueClicked: TransactionState()
    }

    //Exposed LiveData
    private val _state = MutableLiveData<TransactionState>()
    val state: LiveData<TransactionState> = _state


    fun onContinueClicked() {
        _state.value = TransactionState.ContinueClicked
    }

    fun addTransaction(): LiveData<ActionResponse> {
        return addTransactionUseCase.execute(currentTransaction, selectedAccount)
    }

    private val currentTransaction: Transaction
        get() = TransactionProfile(
                    value,
                    Timestamp(System.currentTimeMillis()),
                    description,
                    transactionType,
                    categories.value?.get(selectedCategoryPosition)!!,
                    accounts.value?.get(selectedAccountPosition)!!
                ).toTransaction()
}