package com.schweitzering.domain.fixedExpenses

import com.schweitzering.domain.transaction.TransactionCategory
import java.sql.Timestamp

data class FixedExpense(
    var id: Int = 0,
    var value: Float,
    var category: TransactionCategory = TransactionCategory.EXPENSE, //Each Transaction has a category
    var categoryType: String,
    var isAlreadyPaid: Boolean = false, //In the current period
    var creationDate: Timestamp, //Regarding the period where the fixed expense was created
    var paymentDate: Timestamp? = null
)