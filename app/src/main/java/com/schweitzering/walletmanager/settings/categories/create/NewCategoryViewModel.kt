package com.schweitzering.walletmanager.settings.categories.create

import android.content.Intent
import com.schweitzering.domain.categories.AddCategoryTypeUseCase
import com.schweitzering.domain.categories.CategoryType
import com.schweitzering.domain.transaction.TransactionCategory
import com.schweitzering.walletmanager.settings.categories.create.NewCategoryActivity.Companion.NEW_CATEGORY_TYPE

class NewCategoryViewModel(private val addCategoryTypeUseCase: AddCategoryTypeUseCase) {

    val categoryType = CategoryType()

    fun onSaveClicked() {
        addCategoryTypeUseCase.execute(categoryType)
    }

    fun handleIntent(intent: Intent?) {
        categoryType.category = intent?.getSerializableExtra(NEW_CATEGORY_TYPE) as TransactionCategory
    }
}