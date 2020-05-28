package com.schweitzering.walletmanager.settings.categories.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.schweitzering.domain.categories.CategoryType
import com.schweitzering.domain.categories.GetAllCategoriesTypesUseCase
import com.schweitzering.domain.transaction.TransactionCategory

class CategoriesSettingsViewModel(private val getAllCategoriesTypesUseCase: GetAllCategoriesTypesUseCase) {

    sealed class State {
        class CreateCategory(val category: TransactionCategory) : State()
        class EditCategory(type: CategoryType) : State()
    }

    val categories = Transformations.map(getAllCategoriesTypesUseCase.execute()) {
        it.sortedBy { it.category.name }
    }
    val state = MutableLiveData<State>()

    fun onCreateIncomeClicked() {
        state.value = State.CreateCategory(TransactionCategory.INCOME)
    }

    fun onCreateExpenseClicked() {
        state.value = State.CreateCategory(TransactionCategory.EXPENSE)
    }

    fun onCreateSavingClicked() {
        state.value = State.CreateCategory(TransactionCategory.SAVING)
    }

    fun onCreateInvestmentClicked() {
        state.value = State.CreateCategory(TransactionCategory.INVESTMENT)
    }

    fun onEditCategoryClicked(category: CategoryType) {
        state.value = State.EditCategory(category)
    }
}