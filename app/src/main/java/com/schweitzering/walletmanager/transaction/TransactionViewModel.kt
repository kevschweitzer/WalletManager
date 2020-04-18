package com.schweitzering.walletmanager.transaction

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.schweitzering.domain.transaction.TransactionCategory
import com.schweitzering.walletmanager.R
import com.schweitzering.walletmanager.utils.Constants.Companion.TRANSACTION_CATEGORY

class TransactionViewModel: ViewModel() {

    sealed class TransactionState {
        data class FullLoaded(val categoriesResource: Int): TransactionState()
        object Finished: TransactionState()
    }

    //Exposed LiveData
    var state = MutableLiveData<TransactionState>()


    private lateinit var transactionCategory: TransactionCategory

    fun handleIntent(intent: Intent?) {
        intent?.let {
            transactionCategory = intent.getSerializableExtra(TRANSACTION_CATEGORY) as TransactionCategory
            setPredefinedCategories()
        }
    }

    private fun setPredefinedCategories() {
        state.value = TransactionState.FullLoaded(
            when(transactionCategory) {
            TransactionCategory.INCOME -> R.array.income_categories
            TransactionCategory.EXPENSE -> R.array.expense_categories
            TransactionCategory.SAVING -> R.array.saving_categories
            TransactionCategory.INVESTMENT -> R.array.investment_categories
            }
        )
    }

    fun onContinueClicked() {
        //TODO: Save transaction

        state.value = TransactionState.Finished
    }
}
