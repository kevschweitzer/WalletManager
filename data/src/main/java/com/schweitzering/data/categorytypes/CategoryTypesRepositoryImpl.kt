package com.schweitzering.data.categorytypes

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.schweitzering.data.R
import com.schweitzering.domain.categories.CategoryTypesRepository
import com.schweitzering.domain.transaction.TransactionCategory

class CategoryTypesRepositoryImpl(private val context: Context,
                                  private val database: CategoryTypesDatabaseManager): CategoryTypesRepository {

    override fun getCategoryTypes(category: TransactionCategory): LiveData<List<String>> {
       return Transformations.map(database.getAllByCategory(category)) {
           val predefinedCategories = when(category) {
               TransactionCategory.EXPENSE -> context.resources.getStringArray(R.array.expense_categories)
               TransactionCategory.INCOME -> context.resources.getStringArray(R.array.income_categories)
               TransactionCategory.INVESTMENT -> context.resources.getStringArray(R.array.investment_categories)
               TransactionCategory.SAVING -> context.resources.getStringArray(R.array.saving_categories)
           }
           predefinedCategories.toMutableList().addAll(it)
           predefinedCategories.toList()
       }
    }

    override fun addCategoryType(category: TransactionCategory, type: String) {
    }

    override fun removeCategoryType(category: TransactionCategory, type: String) {
    }
}