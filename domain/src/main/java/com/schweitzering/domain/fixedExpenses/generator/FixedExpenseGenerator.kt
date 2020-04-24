package com.schweitzering.domain.fixedExpenses.generator

import com.schweitzering.domain.schedule.Schedule
import java.sql.Timestamp

data class FixedExpenseGenerator(var id: Int,
                                 var value: Float,
                                 var categoryType: String,
                                 var creationDate: Timestamp,
                                 var schedule: Schedule //Schedule the expense to renew every X days, weeks, months.
)
