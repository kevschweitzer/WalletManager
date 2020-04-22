package com.schweitzering.walletmanager.fixedExpenses

import com.schweitzering.domain.fixedExpenses.Period
import com.schweitzering.domain.fixedExpenses.RenewalDays
import com.schweitzering.domain.fixedExpenses.TimePeriod
import com.schweitzering.domain.transaction.Transaction
import java.sql.Timestamp

data class FixedExpenseProfile(
    var expense: Transaction,
    var isAlreadyPaid: Boolean, //In the current period
    var period: Period,
    var startDate: Timestamp,
    var creationDate: Timestamp
)

class PeriodProfile(var everyXTime: Int, var timePeriod: TimePeriod, var renewalDay: RenewalDays)