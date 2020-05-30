package com.schweitzering.walletmanager.settings.categories.create

import android.content.Intent
import com.schweitzering.domain.categories.AddTransactionCategoryUseCase
import com.schweitzering.domain.categories.TransactionCategory
import com.schweitzering.walletmanager.settings.categories.create.CRUDCategoryActivity.Companion.CATEGORY_TYPE

class CRUDCategoryViewModel(private val addTransactionCategoryUseCase: AddTransactionCategoryUseCase) {

    var categoryType = TransactionCategory()

    fun onSaveClicked() {
        addTransactionCategoryUseCase.execute(categoryType)
    }

    fun handleIntent(intent: Intent?) {
        categoryType = intent?.getSerializableExtra(CATEGORY_TYPE) as TransactionCategory
    }
}