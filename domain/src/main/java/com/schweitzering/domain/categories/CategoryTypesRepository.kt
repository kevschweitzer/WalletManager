package com.schweitzering.domain.categories

import androidx.lifecycle.LiveData
import com.schweitzering.domain.transaction.TransactionCategory

interface CategoryTypesRepository {

    fun getCategoryTypes(category: TransactionCategory): LiveData<List<CategoryType>>

    fun addCategoryType(categoryType: CategoryType)

    fun removeCategoryType(categoryType: CategoryType)

    fun getAll(): LiveData<List<CategoryType>>

}