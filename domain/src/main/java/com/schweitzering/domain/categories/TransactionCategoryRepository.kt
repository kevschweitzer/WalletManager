package com.schweitzering.domain.categories

import androidx.lifecycle.LiveData
import com.schweitzering.domain.ActionResponse
import com.schweitzering.domain.transaction.TransactionType

interface TransactionCategoryRepository {

    fun getCategoriesForType(type: TransactionType): LiveData<List<TransactionCategory>>

    fun addCategory(transactionCategory: TransactionCategory)

    fun removeCategory(transactionCategory: TransactionCategory): LiveData<ActionResponse>

    fun getAll(): LiveData<List<TransactionCategory>>

}