package com.schweitzering.domain.fixedExpenses

import com.schweitzering.domain.schedule.Schedule
import com.schweitzering.domain.transaction.Transaction
import java.sql.Timestamp

data class FixedExpense(
    var expense: Transaction,
    var isAlreadyPaid: Boolean, //In the current period
    var creationDate: Timestamp,
    var schedule: Schedule  //Schedule the expense to renew every X days, weeks, months.
)