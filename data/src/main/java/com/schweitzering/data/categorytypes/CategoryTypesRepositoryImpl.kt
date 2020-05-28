package com.schweitzering.data.categorytypes

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.schweitzering.data.R
import com.schweitzering.data.xsupport.mappers.toCategoryTypeEntity
import com.schweitzering.domain.categories.CategoryType
import com.schweitzering.domain.categories.CategoryTypesRepository
import com.schweitzering.domain.transaction.TransactionCategory

class CategoryTypesRepositoryImpl(private val context: Context,
                                  private val database: CategoryTypesDatabaseManager): CategoryTypesRepository {



    override fun getCategoryTypes(category: TransactionCategory): LiveData<List<CategoryType>> {
       return Transformations.map(database.getAllByCategory(category)) {
           with(getPredefinedCategories(category)) {
               toMutableList().addAll(it)
               toList()
           }
       }
    }

    override fun addCategoryType(categoryType: CategoryType) {
        database.add(categoryType.toCategoryTypeEntity())
    }

    override fun removeCategoryType(categoryType: CategoryType) {
        database.remove(categoryType.toCategoryTypeEntity())
    }

    override fun getAll() = Transformations.map(database.getAll()) {
       getAllPredefinedCategories().apply {
           addAll(it)
       }.toList()
    }

    private fun getPredefinedCategories(category: TransactionCategory) = when(category) {
        TransactionCategory.EXPENSE -> context.resources.getStringArray(R.array.expense_categories).map {
            CategoryType(category = TransactionCategory.EXPENSE, type = it)
        }
        TransactionCategory.INCOME -> context.resources.getStringArray(R.array.income_categories).map {
            CategoryType(category = TransactionCategory.INCOME, type = it)
        }
        TransactionCategory.INVESTMENT -> context.resources.getStringArray(R.array.investment_categories).map {
            CategoryType(category = TransactionCategory.INVESTMENT, type = it)
        }
        TransactionCategory.SAVING -> context.resources.getStringArray(R.array.saving_categories).map {
            CategoryType(category = TransactionCategory.SAVING, type = it)
        }
    }

    private fun getAllPredefinedCategories(): MutableList<CategoryType> {
        val allCategories = mutableListOf<CategoryType>()
        enumValues<TransactionCategory>().forEach {
            allCategories.addAll(getPredefinedCategories(it))
        }
        return allCategories
    }
}