package com.schweitzering.domain.categories

import com.schweitzering.domain.transaction.TransactionType
import java.io.Serializable

data class TransactionCategory(
    var id: Int = 0,
    var type: TransactionType = TransactionType.EXPENSE,
    var name: String = ""
): Serializable