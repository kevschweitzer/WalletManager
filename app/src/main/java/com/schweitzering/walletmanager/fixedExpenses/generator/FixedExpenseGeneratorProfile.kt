package com.schweitzering.walletmanager.fixedExpenses.generator

import com.schweitzering.domain.schedule.Schedule
import com.schweitzering.domain.transaction.Transaction
import java.sql.Timestamp

data class FixedExpenseGeneratorProfile(var id: Int? = null,
                                        var expense: Transaction,
                                        var creationDate: Timestamp,
                                        var schedule: Schedule)