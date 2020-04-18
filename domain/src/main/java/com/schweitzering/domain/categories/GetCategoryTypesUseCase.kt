package com.schweitzering.domain.categories

import com.schweitzering.domain.transaction.TransactionCategory

class GetCategoryTypesUseCase(private val repository: CategoryTypesRepository) {

    fun execute(category: TransactionCategory) = repository.getCategoryTypes(category)

}