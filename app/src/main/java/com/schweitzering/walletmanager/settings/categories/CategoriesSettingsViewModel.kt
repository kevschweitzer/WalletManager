package com.schweitzering.walletmanager.settings.categories

import androidx.lifecycle.Transformations
import com.schweitzering.domain.categories.GetAllCategoriesTypesUseCase

class CategoriesSettingsViewModel(private val getAllCategoriesTypesUseCase: GetAllCategoriesTypesUseCase) {

    val categories = Transformations.map(getAllCategoriesTypesUseCase.execute()) {
        it.sortedBy { it.category.name }
    }
}