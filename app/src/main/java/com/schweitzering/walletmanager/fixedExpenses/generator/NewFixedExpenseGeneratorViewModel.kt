package com.schweitzering.walletmanager.fixedExpenses.generator

import androidx.lifecycle.MutableLiveData
import com.schweitzering.domain.fixedExpenses.NewFixedExpenseUseCase
import com.schweitzering.domain.fixedExpenses.generator.FixedExpenseGenerator
import com.schweitzering.domain.fixedExpenses.generator.NewFixedExpenseGeneratorUseCase
import com.schweitzering.domain.schedule.Schedule
import com.schweitzering.domain.schedule.TimePeriod
import com.schweitzering.walletmanager.xsupport.mappers.toFixedExpenseGenerator
import java.sql.Timestamp

class NewFixedExpenseGeneratorViewModel(private val newFixedExpenseGeneratorUseCase: NewFixedExpenseGeneratorUseCase) {

    sealed class State {
        object FixedExpenseCreationSuccess: State()
    }

    companion object {
        const val ONE_DAY_IN_MILLIS = 86400000
    }
    var value: Float = 120f
    var categoryType: String = "Gym"
    var period = TimePeriod.WEEK
    var startDate: Timestamp = Timestamp(1587477600000)

    //Exposed
    val state = MutableLiveData<State>()

    fun onCreateClicked() {
        newFixedExpenseGeneratorUseCase.execute(getCurrentFixedExpenseGenerator().toFixedExpenseGenerator())
        state.value = State.FixedExpenseCreationSuccess
    }

    private fun getCurrentFixedExpenseGenerator() = FixedExpenseGeneratorProfile(
        value = value,
        categoryType = categoryType,
        creationDate = Timestamp(System.currentTimeMillis()),
        schedule = Schedule(period, startDate)
    )
}