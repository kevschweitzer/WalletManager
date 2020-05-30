package com.schweitzering.domain.categories

class AddTransactionCategoryUseCase(private val repository: TransactionCategoryRepository) {

    fun execute(transactionCategory: TransactionCategory) = repository.addCategory(transactionCategory)
}