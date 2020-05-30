package com.schweitzering.domain.categories

import com.schweitzering.domain.transaction.TransactionType

class GetTransactionCategoriesForTypeUseCase(private val repository: TransactionCategoryRepository) {

    fun execute(type: TransactionType) = repository.getCategoriesForType(type)
}