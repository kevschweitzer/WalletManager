package com.schweitzering.domain.debts

import com.schweitzering.domain.categories.TransactionCategory
import com.schweitzering.domain.transaction.TransactionType
import java.sql.Timestamp

class Debt(
    var id: Int? = null,
    var value: Float,
    var description: String,
    var category: TransactionCategory,
    var creationDate: Timestamp,
    var type: TransactionType,
    var isResolved: Boolean = false, //Paid or charged regarding category (expense, income)
    var resolveDate: Timestamp? = null //Date to pay/charge this debt
)