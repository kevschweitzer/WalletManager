package com.schweitzering.domain.categories

class GetAllTransactionCategoriesUseCase(private val repository: TransactionCategoryRepository) {

    fun execute() = repository.getAll()
}