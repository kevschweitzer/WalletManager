package com.schweitzering.domain.fixedExpenses

import androidx.lifecycle.Transformations
import com.schweitzering.domain.fixedExpenses.generator.FixedExpenseGenerator
import com.schweitzering.domain.fixedExpenses.generator.FixedExpensesGeneratorRepository
import com.schweitzering.domain.schedule.TimePeriod
import com.schweitzering.domain.transaction.TransactionCategory
import io.reactivex.Maybe
import java.sql.Timestamp
import java.util.*

/*
* Check Fixed Expenses generators period and create a FixedExpense for the current period if necessary.
* Example: MONTH will create one every 1st of month, DAY will create new one every day
*/
class CreateFixedExpensesForPeriodUseCase(private val generatorsRepository: FixedExpensesGeneratorRepository,
                                            private val fixedExpensesRepository: FixedExpensesRepository) {

    fun execute(): Maybe<List<FixedExpenseGenerator>> {
        return generatorsRepository.getAll().doOnSuccess {
            it.forEach {
                if (it.schedule.todayMeetsRequirements()) {
                    val fixedExpense = FixedExpense(
                        value = it.value,
                        categoryType = it.categoryType,
                        creationDate = Timestamp(System.currentTimeMillis())
                    )
                    fixedExpensesRepository.addFixedExpense(fixedExpense)
                }
            }
        }
    }
}