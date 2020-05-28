package com.schweitzering.walletmanager.settings.categories.create

import android.content.Intent
import android.util.Log
import com.schweitzering.domain.categories.CategoryType
import com.schweitzering.domain.transaction.TransactionCategory
import com.schweitzering.walletmanager.settings.categories.create.NewCategoryActivity.Companion.NEW_CATEGORY_TYPE

class NewCategoryViewModel {

    val categoryType = CategoryType()

    fun onSaveClicked() {
        Log.e("Category", categoryType.toString())
    }

    fun handleIntent(intent: Intent?) {
        categoryType.category = intent?.getSerializableExtra(NEW_CATEGORY_TYPE) as TransactionCategory
    }
}