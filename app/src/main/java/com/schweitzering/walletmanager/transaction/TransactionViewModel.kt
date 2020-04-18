package com.schweitzering.walletmanager.transaction

import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.schweitzering.domain.finance.FinanceCategory
import com.schweitzering.walletmanager.utils.Constants.Companion.FINANCE_CATEGORY
import com.schweitzering.walletmanager.R

class TransactionViewModel: ViewModel() {

    sealed class TransactionState {
        data class FullLoaded(val categoriesResource: Int): TransactionState()
        object Finished: TransactionState()
    }

    //Exposed LiveData
    var state = MutableLiveData<TransactionState>()


    private lateinit var financeCategory: FinanceCategory

    fun handleIntent(intent: Intent?) {
        intent?.let {
            financeCategory = intent.getSerializableExtra(FINANCE_CATEGORY) as FinanceCategory
            setPredefinedCategories()
        }
    }

    private fun setPredefinedCategories() {
        state.value = TransactionState.FullLoaded(
            when(financeCategory) {
            FinanceCategory.INCOME -> R.array.income_categories
            FinanceCategory.EXPENSE -> R.array.expense_categories
            FinanceCategory.SAVING -> R.array.saving_categories
            FinanceCategory.INVESTMENT -> R.array.investment_categories
            }
        )
    }

    fun onContinueClicked() {
        //TODO: Save transaction

        state.value = TransactionState.Finished
    }
}
