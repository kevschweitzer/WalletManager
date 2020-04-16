package com.schweitzering.domain.finance

import java.sql.Timestamp
import java.time.LocalDateTime

data class Finance(
    var value: Float,
    var date: Timestamp,
    var category: FinanceCategory, //Each Finance has a category
    var financeCategoryType: String //Each category has an associated type. For example Expense can be food, house, clothes, etc.
)

enum class FinanceCategory {
    INCOME, EXPENSE, SAVING, INVESTMENT
}