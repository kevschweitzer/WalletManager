package com.schweitzering.domain.categories

import androidx.lifecycle.LiveData
import com.schweitzering.domain.transaction.TransactionType

interface TransactionCategoryRepository {

    fun getCategoriesForType(type: TransactionType): LiveData<List<TransactionCategory>>

    fun addCategory(transactionCategory: TransactionCategory)

    fun removeCategory(transactionCategory: TransactionCategory)

    fun getAll(): LiveData<List<TransactionCategory>>

}