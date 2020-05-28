package com.schweitzering.walletmanager.settings.categories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.schweitzering.domain.categories.CategoryType
import com.schweitzering.domain.categories.GetAllCategoriesTypesUseCase

class CategoriesSettingsViewModel(private val getAllCategoriesTypesUseCase: GetAllCategoriesTypesUseCase) {

    sealed class State {
        class CreateCategory(category: CategoryType): State()
        class EditCategory(category: CategoryType): State()
    }

    val categories = Transformations.map(getAllCategoriesTypesUseCase.execute()) {
        it.sortedBy { it.category.name }
    }
    val state = MutableLiveData<State>()

    fun onCreateCategoryClicked(category: CategoryType) {
        state.value = State.CreateCategory(category)
    }

    fun onEditCategoryClicked(category: CategoryType) {
        state.value = State.EditCategory(category)
    }
}