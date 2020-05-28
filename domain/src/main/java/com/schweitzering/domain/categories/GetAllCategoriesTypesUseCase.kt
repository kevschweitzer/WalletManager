package com.schweitzering.domain.categories

class GetAllCategoriesTypesUseCase(private val repository: CategoryTypesRepository) {

    fun execute() = repository.getAll()
}