package com.schweitzering.walletmanager.commons

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.schweitzering.domain.accounts.Account
import com.schweitzering.domain.accounts.GetAllAccountsUseCase
import com.schweitzering.domain.categories.GetTransactionCategoriesForTypeUseCase
import com.schweitzering.domain.categories.TransactionCategory
import com.schweitzering.domain.transaction.TransactionType
import com.schweitzering.walletmanager.xsupport.utils.Constants

abstract class BaseTransactionViewModel(
    private val getTransactionCategoriesForTypeUseCase: GetTransactionCategoriesForTypeUseCase,
    private val getAllAccountsUseCase: GetAllAccountsUseCase
): ViewModel() {

    lateinit var transactionType: TransactionType
    val categories: LiveData<List<TransactionCategory>> by lazy {
        getTransactionCategoriesForTypeUseCase.execute(transactionType)
    }
    val accounts = getAllAccountsUseCase.execute()

    val selectedAccount: Account?
        get() = accounts.value?.get(selectedAccountPosition)

    //UI fields
    var value: Float = 10f
    var description: String = ""
    var selectedCategoryPosition: Int = 0
    var selectedAccountPosition: Int = 0

    fun handleIntent(intent: Intent?) {
        intent?.let {
            transactionType =
                intent.getSerializableExtra(Constants.TRANSACTION_CATEGORY) as TransactionType
        }
    }
}