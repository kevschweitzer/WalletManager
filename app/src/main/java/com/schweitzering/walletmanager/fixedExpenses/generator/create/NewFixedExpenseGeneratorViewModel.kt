package com.schweitzering.walletmanager.fixedExpenses.generator.create

import androidx.lifecycle.MutableLiveData
import com.schweitzering.domain.accounts.Account
import com.schweitzering.domain.accounts.GetAllAccountsUseCase
import com.schweitzering.domain.categories.GetTransactionCategoriesForTypeUseCase
import com.schweitzering.domain.categories.TransactionCategory
import com.schweitzering.domain.fixedExpenses.generator.NewFixedExpenseGeneratorUseCase
import com.schweitzering.domain.schedule.Schedule
import com.schweitzering.domain.schedule.TimePeriod
import com.schweitzering.domain.transaction.Transaction
import com.schweitzering.walletmanager.commons.BaseTransactionViewModel
import com.schweitzering.walletmanager.fixedExpenses.generator.FixedExpenseGeneratorProfile
import com.schweitzering.walletmanager.xsupport.mappers.toFixedExpenseGenerator
import java.sql.Timestamp

class NewFixedExpenseGeneratorViewModel(
    private val newFixedExpenseGeneratorUseCase: NewFixedExpenseGeneratorUseCase,
    getTransactionCategoriesForTypeUseCase: GetTransactionCategoriesForTypeUseCase,
    getAllAccountsUseCase: GetAllAccountsUseCase
): BaseTransactionViewModel(getTransactionCategoriesForTypeUseCase, getAllAccountsUseCase) {

    sealed class State {
        object FixedExpenseCreationSuccess : State()
    }

    var categoryType: String = "Gym"
    var period = TimePeriod.WEEK
    var startDate: Timestamp = Timestamp(1587477600000)

    //Exposed
    val state = MutableLiveData<State>()

    private fun getWeekFixedExpenseGenerator() = FixedExpenseGeneratorProfile(expense = Transaction(
        value = 120f,
        description = "",
        category = TransactionCategory(),
        account = Account()),
        creationDate = Timestamp(System.currentTimeMillis()),
        schedule = Schedule(TimePeriod.WEEK, Timestamp(1589425200000)))

    private fun getMonthFixedExpenseGenerator() =
        FixedExpenseGeneratorProfile(expense = Transaction(value = 2400f,
            description = "",
            category = TransactionCategory(),
            account = Account()),
            creationDate = Timestamp(System.currentTimeMillis()),
            schedule = Schedule(TimePeriod.MONTH, Timestamp(1591585200000)))

    private fun getDayFixedExpenseGenerator() = FixedExpenseGeneratorProfile(expense = Transaction(
        value = 25f,
        description = "",
        category = TransactionCategory(),
        account = Account()),
        creationDate = Timestamp(System.currentTimeMillis()),
        schedule = Schedule(TimePeriod.DAY, Timestamp(1584045000000)))

    private fun getCurrentFixedExpenseGenerator() =
        FixedExpenseGeneratorProfile(expense = Transaction(value = 120f,
            description = "",
            category = TransactionCategory(),
            account = Account()),
            creationDate = Timestamp(System.currentTimeMillis()),
            schedule = Schedule(period, startDate))

    override fun onContinueClicked() {
        state.value = State.FixedExpenseCreationSuccess
    }
}