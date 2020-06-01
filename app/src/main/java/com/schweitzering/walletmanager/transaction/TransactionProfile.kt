package com.schweitzering.walletmanager.transaction

import com.schweitzering.domain.categories.TransactionCategory
import com.schweitzering.domain.transaction.TransactionType
import java.sql.Timestamp

data class TransactionProfile(var value: Float,
                              var date: Timestamp?,
                              var description: String,
                              var type: TransactionType,
                              var category: TransactionCategory)