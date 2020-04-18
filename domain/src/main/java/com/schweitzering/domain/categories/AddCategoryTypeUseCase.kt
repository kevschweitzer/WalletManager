package com.schweitzering.domain.categories

class AddCategoryTypeUseCase(private val repository: CategoryTypesRepository) {

    fun execute(categoryType: CategoryType) = repository.addCategoryType(categoryType)
}