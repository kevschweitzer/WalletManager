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



    override fun getCategoriesForType(type: TransactionType) =
        Transformations.map(databaseTransaction.getAllByType(type)) {
           it.map { it.toTransactionCategory() }
        }

    override fun addCategory(transactionCategory: TransactionCategory) {
        databaseTransaction.add(transactionCategory.toTransactionCategoryEntity())
    }

    override fun removeCategory(transactionCategory: TransactionCategory) {
        databaseTransaction.remove(transactionCategory.toTransactionCategoryEntity())
    }

    override fun getAll() = Transformations.map(databaseTransaction.getAll()) {
       it.map { it.toTransactionCategory() }
    }


}