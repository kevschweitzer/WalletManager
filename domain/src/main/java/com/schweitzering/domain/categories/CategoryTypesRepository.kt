package com.schweitzering.domain.categories

import androidx.lifecycle.LiveData
import com.schweitzering.domain.transaction.TransactionCategory

interface CategoryTypesRepository {

    fun getCategoryTypes(category: TransactionCategory): LiveData<List<String>>

    fun addCategoryType(category: TransactionCategory, type: String)

    fun removeCategoryType(category: TransactionCategory, type: String)

}