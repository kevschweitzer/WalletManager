package com.schweitzering.domain.categories

import com.schweitzering.domain.transaction.TransactionCategory

data class CategoryType(
    var category: TransactionCategory = TransactionCategory.EXPENSE,
    var type: String = ""
)