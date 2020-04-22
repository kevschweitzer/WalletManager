package com.schweitzering.walletmanager.fixedExpenses

import com.schweitzering.domain.transaction.Transaction
import java.sql.Timestamp

data class FixedExpenseProfile(
    var expense: Transaction,
    var isAlreadyPaid: Boolean, //In the current period
    var creationDate: Timestamp
)