package com.schweitzering.walletmanager.fixedExpenses

import com.schweitzering.domain.transaction.TransactionCategory
import java.sql.Timestamp

data class FixedExpenseProfile(
    var id: Int,
    var value: Float,
    var category: TransactionCategory,
    var categoryType: String,
    var isAlreadyPaid: Boolean,
    var creationDate: Timestamp,
    var paymentDate: Timestamp?
)