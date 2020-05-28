package com.schweitzering.domain.categories

import com.schweitzering.domain.transaction.TransactionCategory
import java.io.Serializable

data class CategoryType(
    var id: Int = 0,
    var category: TransactionCategory = TransactionCategory.EXPENSE,
    var type: String = ""
): Serializable