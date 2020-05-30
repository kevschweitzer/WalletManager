package com.schweitzering.domain.categories

class DeleteTransactionCategoryUseCase(private val repository: TransactionCategoryRepository) {

    fun execute(transactionCategory: TransactionCategory) = repository.removeCategory(transactionCategory)
}