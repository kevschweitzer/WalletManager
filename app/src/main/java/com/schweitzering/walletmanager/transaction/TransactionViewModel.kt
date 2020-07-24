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
import com.schweitzering.walletmanager.xsupport.mappers.toTransaction
import com.schweitzering.walletmanager.xsupport.utils.Constants.Companion.TRANSACTION_CATEGORY
import java.sql.Timestamp

class TransactionViewModel(private val addTransactionUseCase: AddTransactionUseCase,
                           private val getTransactionCategoriesForTypeUseCase: GetTransactionCategoriesForTypeUseCase,
                           private val getAllAccountsUseCase: GetAllAccountsUseCase) :
    ViewModel() {

    sealed class TransactionState {
        object ContinueClicked: TransactionState()
    }

    //Local variables
    private lateinit var transactionType: TransactionType

    //Exposed LiveData
    private val _state = MutableLiveData<TransactionState>()
    val state: LiveData<TransactionState> = _state
    val categories: LiveData<List<TransactionCategory>> by lazy {
        getTransactionCategoriesForTypeUseCase.execute(transactionType)
    }
    val accounts = getAllAccountsUseCase.execute()

    //UI fields
    var value: Float = 10f
    var description: String = ""
    var selectedCategoryPosition: Int = 0
    var selectedAccountPosition: Int = 0
    val selectedAccount: Account?
        get() = accounts.value?.get(selectedAccountPosition)

    fun handleIntent(intent: Intent?) {
        intent?.let {
            transactionType =
                intent.getSerializableExtra(TRANSACTION_CATEGORY) as TransactionType
        }
    }

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