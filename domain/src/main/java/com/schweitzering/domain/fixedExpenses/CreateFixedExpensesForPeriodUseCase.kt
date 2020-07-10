package com.schweitzering.domain.fixedExpenses

import com.schweitzering.domain.fixedExpenses.generator.FixedExpenseGenerator
import com.schweitzering.domain.fixedExpenses.generator.FixedExpensesGeneratorRepository
import io.reactivex.Single
import java.sql.Timestamp

/*
* Check Fixed Expenses generators period and create a FixedExpense for the current period if necessary.
* Example: MONTH will create one every 1st of month, DAY will create new one every day
*/
class CreateFixedExpensesForPeriodUseCase(private val generatorsRepository: FixedExpensesGeneratorRepository,
                                            private val fixedExpensesRepository: FixedExpensesRepository) {

    fun execute(): Single<List<FixedExpenseGenerator>> {
        return generatorsRepository.getAll().doOnSuccess {
            it.forEach {
                if (it.schedule.todayMeetsRequirements()) {
                    val fixedExpense = FixedExpense(
                        expense = it.expense,
                        creationDate = Timestamp(System.currentTimeMillis())
                    )
                    fixedExpensesRepository.insert(fixedExpense)
                }
            }
        }
    }
}