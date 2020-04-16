package com.schweitzering.data.mappers

import com.schweitzering.data.finance.FinanceEntity
import com.schweitzering.domain.finance.Finance

fun Finance.toFinanceEntity() = FinanceEntity(
    value = value,
    date = date,
    category = category,
    financeCategoryType = financeCategoryType
)

fun FinanceEntity.toFinance() = Finance(value = value, date = date, category = category, financeCategoryType = financeCategoryType)