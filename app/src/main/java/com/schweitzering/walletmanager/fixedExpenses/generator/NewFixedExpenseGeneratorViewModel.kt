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
        newFixedExpenseGeneratorUseCase.execute(getDayFixedExpenseGenerator().toFixedExpenseGenerator())
        newFixedExpenseGeneratorUseCase.execute(getMonthFixedExpenseGenerator().toFixedExpenseGenerator())
        newFixedExpenseGeneratorUseCase.execute(getWeekFixedExpenseGenerator().toFixedExpenseGenerator())
        state.value = State.FixedExpenseCreationSuccess
    }

    private fun getWeekFixedExpenseGenerator() = FixedExpenseGeneratorProfile (
        value = 120f,
        categoryType = "Gym",
        creationDate = Timestamp(System.currentTimeMillis()),
        schedule = Schedule(TimePeriod.WEEK, Timestamp(1587477600000))
    )

    private fun getMonthFixedExpenseGenerator() = FixedExpenseGeneratorProfile (
        value = 240f,
        categoryType = "Clothes",
        creationDate = Timestamp(System.currentTimeMillis()),
        schedule = Schedule(TimePeriod.MONTH, Timestamp(1585200600000))
    )

    private fun getDayFixedExpenseGenerator() = FixedExpenseGeneratorProfile (
        value = 25f,
        categoryType = "Food",
        creationDate = Timestamp(System.currentTimeMillis()),
        schedule = Schedule(TimePeriod.DAY, Timestamp(1584045000000))
    )

    private fun getCurrentFixedExpenseGenerator() = FixedExpenseGeneratorProfile(
        value = value,
        categoryType = categoryType,
        creationDate = Timestamp(System.currentTimeMillis()),
        schedule = Schedule(period, startDate)
    )
}