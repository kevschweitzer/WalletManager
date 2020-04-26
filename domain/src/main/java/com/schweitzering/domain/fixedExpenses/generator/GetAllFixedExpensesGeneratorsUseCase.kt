package com.schweitzering.domain.fixedExpenses.generator

import com.schweitzering.domain.schedule.TimePeriod
import java.sql.Timestamp

class GetAllFixedExpensesGeneratorsUseCase(private val repository: FixedExpensesGeneratorRepository) {

    fun execute() = repository.getAll()
}