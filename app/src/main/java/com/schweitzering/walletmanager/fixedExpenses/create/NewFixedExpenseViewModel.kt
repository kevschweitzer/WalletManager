package com.schweitzering.walletmanager.fixedExpenses.create

import com.schweitzering.data.transaction.TransactionEntity
import com.schweitzering.data.xsupport.mappers.toTransaction
import com.schweitzering.domain.fixedExpenses.NewFixedExpenseUseCase
import com.schweitzering.domain.transaction.TransactionCategory
import com.schweitzering.walletmanager.fixedExpenses.FixedExpenseProfile
import java.sql.Timestamp

class NewFixedExpenseViewModel(private val newFixedExpenseUseCase: NewFixedExpenseUseCase) {

    companion object {
        const val ONE_DAY_IN_MILLIS = 86400000
    }
    var value: Float = 500f
    var categoryType: String = "Food"
    var startDate: Timestamp = Timestamp(System.currentTimeMillis() + ONE_DAY_IN_MILLIS*2)

    fun onCreateClicked() {
        //newFixedExpenseUseCase.execute(getCurrentFixedExpense().toFixedExpense())
    }

    private fun getCurrentFixedExpense() = FixedExpenseProfile(
        expense = TransactionEntity(value = value, date = Timestamp(System.currentTimeMillis()),
            category = TransactionCategory.EXPENSE, categoryType = categoryType).toTransaction(),
        isAlreadyPaid = false,
        creationDate = Timestamp(System.currentTimeMillis())
    )
}