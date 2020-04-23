package com.schweitzering.walletmanager.fixedExpenses

import com.schweitzering.domain.schedule.Schedule
import com.schweitzering.domain.transaction.Transaction
import java.sql.Timestamp

data class FixedExpenseProfile(
    var id: Int,
    var expense: Transaction,
    var isAlreadyPaid: Boolean, //In the current period
    var creationDate: Timestamp,
    var schedule: Schedule
)