package com.schweitzering.walletmanager.fixedExpenses.generator

import com.schweitzering.domain.schedule.Schedule
import java.sql.Timestamp

data class FixedExpenseGeneratorProfile (var id: Int? = null,
                                         var value: Float,
                                         var categoryType: String,
                                         var creationDate: Timestamp,
                                         var schedule: Schedule
)