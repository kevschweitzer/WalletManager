package com.schweitzering.walletmanager.settings.categories.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.schweitzering.domain.categories.TransactionCategory
import com.schweitzering.domain.categories.GetAllTransactionCategoriesUseCase
import com.schweitzering.domain.transaction.TransactionType

class CategoriesSettingsViewModel(private val getAllTransactionCategoriesUseCase: GetAllTransactionCategoriesUseCase) {

    sealed class State {
        class CreateCategory(val type: TransactionType) : State()
        class EditCategory(val category: TransactionCategory) : State()
        class DeleteCategory(val category: TransactionCategory): State()
    }

    val categories = Transformations.map(getAllTransactionCategoriesUseCase.execute()) {
        it.sortedBy { it.name }
    }
    val state = MutableLiveData<State>()

    fun onCreateIncomeClicked() {
        state.value = State.CreateCategory(TransactionType.INCOME)
    }

    fun onCreateExpenseClicked() {
        state.value = State.CreateCategory(TransactionType.EXPENSE)
    }

    fun onCreateSavingClicked() {
        state.value = State.CreateCategory(TransactionType.SAVING)
    }

    fun onCreateInvestmentClicked() {
        state.value = State.CreateCategory(TransactionType.INVESTMENT)
    }

    fun onEditCategoryClicked(transactionCategory: TransactionCategory) {
        state.value = State.EditCategory(transactionCategory)
    }

    fun onDeleteCategoryClicked(transactionCategory: TransactionCategory) {
        state.value = State.DeleteCategory(transactionCategory)
    }

    fun deleteCategory(category: TransactionCategory) {

    }
}