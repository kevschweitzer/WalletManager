package com.schweitzering.data.categories

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.schweitzering.data.R
import com.schweitzering.data.xsupport.mappers.toTransactionCategory
import com.schweitzering.data.xsupport.mappers.toTransactionCategoryEntity
import com.schweitzering.domain.categories.TransactionCategory
import com.schweitzering.domain.categories.TransactionCategoryRepository
import com.schweitzering.domain.transaction.TransactionType

class TransactionCategoryRepositoryImpl(private val context: Context,
                                        private val databaseTransaction: TransactionCategoryDatabaseManager): TransactionCategoryRepository {



    override fun getCategoriesForType(type: TransactionType): LiveData<List<TransactionCategory>> {
       return Transformations.map(databaseTransaction.getAllByType(type)) {
           with(getPredefinedCategories(type)) {
               toMutableList().addAll(it)
               map { it.toTransactionCategory() }
           }
       }
    }

    override fun addCategory(transactionCategory: TransactionCategory) {
        databaseTransaction.add(transactionCategory.toTransactionCategoryEntity())
    }

    override fun removeCategory(transactionCategory: TransactionCategory) {
        databaseTransaction.remove(transactionCategory.toTransactionCategoryEntity())
    }

    override fun getAll() = Transformations.map(databaseTransaction.getAll()) {
       getAllPredefinedCategories().apply {
           addAll(it)
       }.map { it.toTransactionCategory() }
    }

    private fun getPredefinedCategories(type: TransactionType) = when(type) {
        TransactionType.EXPENSE -> context.resources.getStringArray(R.array.expense_categories).map {
            TransactionCategoryEntity(type = TransactionType.EXPENSE, name = it)
        }
        TransactionType.INCOME -> context.resources.getStringArray(R.array.income_categories).map {
            TransactionCategoryEntity(type = TransactionType.INCOME, name = it)
        }
        TransactionType.INVESTMENT -> context.resources.getStringArray(R.array.investment_categories).map {
            TransactionCategoryEntity(type = TransactionType.INVESTMENT, name = it)
        }
        TransactionType.SAVING -> context.resources.getStringArray(R.array.saving_categories).map {
            TransactionCategoryEntity(type = TransactionType.SAVING, name = it)
        }
    }

    private fun getAllPredefinedCategories(): MutableList<TransactionCategoryEntity> {
        val allCategories = mutableListOf<TransactionCategoryEntity>()
        enumValues<TransactionType>().forEach {
            allCategories.addAll(getPredefinedCategories(it))
        }
        return allCategories
    }
}