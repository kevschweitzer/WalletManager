package com.schweitzering.walletmanager.settings.categories.create

import android.content.Intent
import com.schweitzering.domain.categories.AddCategoryTypeUseCase
import com.schweitzering.domain.categories.CategoryType
import com.schweitzering.walletmanager.settings.categories.create.CRUDCategoryActivity.Companion.CATEGORY_TYPE

class CRUDCategoryViewModel(private val addCategoryTypeUseCase: AddCategoryTypeUseCase) {

    var categoryType = CategoryType()

    fun onSaveClicked() {
        addCategoryTypeUseCase.execute(categoryType)
    }

    fun handleIntent(intent: Intent?) {
        categoryType = intent?.getSerializableExtra(CATEGORY_TYPE) as CategoryType
    }
}