package com.schweitzering.domain.fixedExpenses.generator

import com.schweitzering.domain.schedule.Schedule
import com.schweitzering.domain.transaction.Transaction
import java.sql.Timestamp

data class FixedExpenseGenerator(var id: Int?,
                                 var expense: Transaction,
                                 var creationDate: Timestamp,
                                 var schedule: Schedule //Schedule the expense to renew every X days, weeks, months.
)
