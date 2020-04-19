package com.schweitzering.domain.categories

import com.schweitzering.domain.transaction.TransactionCategory

data class CategoryType(
    var category: TransactionCategory,
    var type: String
)