package com.schweitzering.walletmanager.fixedExpenses

import com.schweitzering.domain.transaction.Transaction
import java.sql.Timestamp

data class FixedExpenseProfile(var id: Int,
                               var expense: Transaction,
                               var isAlreadyPaid: Boolean,
                               var creationDate: Timestamp,
                               var paymentDate: Timestamp?)