package com.schweitzering.walletmanager.transaction

import com.schweitzering.domain.transaction.TransactionCategory
import java.sql.Timestamp

data class TransactionProfile (
    var value: Float,
    var date: Timestamp?,
    var description: String,
    var category: TransactionCategory,
    var categoryType: String
)