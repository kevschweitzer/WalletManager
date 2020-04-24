package com.schweitzering.walletmanager.fixedExpenses.create

import androidx.lifecycle.MutableLiveData
import com.schweitzering.data.transaction.TransactionEntity
import com.schweitzering.data.xsupport.mappers.toTransaction
import com.schweitzering.domain.fixedExpenses.NewFixedExpenseUseCase
import com.schweitzering.domain.schedule.TimePeriod
import com.schweitzering.domain.transaction.TransactionCategory
import com.schweitzering.walletmanager.fixedExpenses.FixedExpenseProfile
import com.schweitzering.walletmanager.xsupport.mappers.toFixedExpense
import java.sql.Timestamp

class NewFixedExpenseViewModel(private val newFixedExpenseUseCase: NewFixedExpenseUseCase) {

    sealed class State {
        object FixedExpenseCreationSuccess: State()
    }

    companion object {
        const val ONE_DAY_IN_MILLIS = 86400000
    }
    var value: Float = 500f
    var categoryType: String = "Food"
    var period = TimePeriod.MONTH
    var startDate: Timestamp = Timestamp(System.currentTimeMillis() + ONE_DAY_IN_MILLIS*2)

    //Exposed
    val state = MutableLiveData<State>()

    fun onCreateClicked() {
        newFixedExpenseUseCase.execute(getCurrentFixedExpense().toFixedExpense())
        state.value = State.FixedExpenseCreationSuccess
    }

    private fun getCurrentFixedExpense() = FixedExpenseProfile(
        id = 0,
        expense = TransactionEntity(value = value, date = Timestamp(System.currentTimeMillis()),
            category = TransactionCategory.EXPENSE, categoryType = categoryType).toTransaction(),
        isAlreadyPaid = false,
        creationDate = Timestamp(System.currentTimeMillis())
    )
}