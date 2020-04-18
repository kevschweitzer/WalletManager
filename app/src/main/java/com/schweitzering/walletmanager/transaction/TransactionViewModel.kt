package com.schweitzering.walletmanager.transaction

import android.content.Intent
import androidx.databinding.InverseMethod
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.schweitzering.domain.categories.GetCategoryTypesUseCase
import com.schweitzering.domain.transaction.Transaction
import com.schweitzering.domain.transaction.TransactionCategory
import com.schweitzering.domain.transaction.AddTransactionUseCase
import com.schweitzering.walletmanager.R
import com.schweitzering.walletmanager.mappers.toTransaction
import com.schweitzering.walletmanager.utils.Constants.Companion.TRANSACTION_CATEGORY
import java.sql.Timestamp

class TransactionViewModel(private val addTransactionUseCase: AddTransactionUseCase,
                           private val getCategoryTypeUseCase: GetCategoryTypesUseCase): ViewModel() {

    sealed class TransactionState {
        data class FullLoaded(val categoriesResource: Int): TransactionState()
        object Finished: TransactionState()
    }

    //Local variables
    private lateinit var transactionCategory: TransactionCategory

    //Exposed LiveData
    var state = MutableLiveData<TransactionState>()
    val categories: LiveData<List<String>> by lazy { getCategoryTypeUseCase.execute(transactionCategory) }

    //UI fields
    var value: Float = 0f
    var description: String = ""
    var selectedCategoryPosition: Int = 0

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
        getCurrentTransaction()?.let{addTransactionUseCase.execute(it.toTransaction())}
        state.value = TransactionState.Finished
    }

    private fun getCurrentTransaction(): TransactionProfile? {
        return categories.value?.get(selectedCategoryPosition)?.let {
            TransactionProfile(value, Timestamp(System.currentTimeMillis()), transactionCategory, it)
        }
    }


}

@InverseMethod("convertStringToFloat")
fun convertStringToFloat(value: String) = value.toFloat()

