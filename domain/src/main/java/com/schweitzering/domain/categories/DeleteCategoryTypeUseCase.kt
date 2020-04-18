package com.schweitzering.domain.categories

import com.schweitzering.domain.transaction.TransactionCategory

class DeleteCategoryTypeUseCase(private val repository: CategoryTypesRepository) {

    fun execute(category: TransactionCategory, type: String) = repository.removeCategoryType(category, type)
}