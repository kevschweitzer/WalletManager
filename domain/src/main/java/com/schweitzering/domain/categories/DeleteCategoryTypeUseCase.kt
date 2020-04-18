package com.schweitzering.domain.categories

class DeleteCategoryTypeUseCase(private val repository: CategoryTypesRepository) {

    fun execute(categoryType: CategoryType) = repository.removeCategoryType(categoryType)
}