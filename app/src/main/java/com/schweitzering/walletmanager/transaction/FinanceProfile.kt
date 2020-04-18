package com.schweitzering.walletmanager.transaction

import com.schweitzering.domain.finance.FinanceCategory
import java.sql.Timestamp

data class FinanceProfile (
    var value: Float,
    var date: Timestamp,
    var category: FinanceCategory,
    var financeCategoryType: String
)