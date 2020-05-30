package com.schweitzering.walletmanager.transaction

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.schweitzering.domain.categories.GetTransactionCategoriesForTypeUseCase
import com.schweitzering.domain.categories.TransactionCategory
import com.schweitzering.domain.transaction.AddTransactionUseCase
import com.schweitzering.domain.transaction.TransactionType
import com.schweitzering.walletmanager.xsupport.mappers.toTransaction
import com.schweitzering.walletmanager.xsupport.utils.Constants.Companion.TRANSACTION_CATEGORY
import java.sql.Timestamp

class TransactionViewModel(private val addTransactionUseCase: AddTransactionUseCase,
                           private val getTransactionCategoriesForTypeUseCase: GetTransactionCategoriesForTypeUseCase) :
    ViewModel() {

    sealed class TransactionState {
        object Finished : TransactionState()
    }

    //Local variables
    private lateinit var transactionType: TransactionType

    //Exposed LiveData
    var state = MutableLiveData<TransactionState>()
    val categories: LiveData<List<TransactionCategory>> by lazy {
        getTransactionCategoriesForTypeUseCase.execute(transactionType)
    }

    //UI fields
    var value: Float = 10f
    var description: String = ""
    var selectedCategoryPosition: Int = 0

    fun handleIntent(intent: Intent?) {
        intent?.let {
            transactionType =
                intent.getSerializableExtra(TRANSACTION_CATEGORY) as TransactionType
        }
    }

    fun onContinueClicked() {
        getCurrentTransaction()?.let { addTransactionUseCase.execute(it.toTransaction()) }
        state.value = TransactionState.Finished
    }

    private fun getCurrentTransaction(): TransactionProfile? {
        return categories.value?.get(selectedCategoryPosition)?.let {
            TransactionProfile(value,
                Timestamp(System.currentTimeMillis()),
                description,
                transactionType,
                it.id)
        }
    }
}