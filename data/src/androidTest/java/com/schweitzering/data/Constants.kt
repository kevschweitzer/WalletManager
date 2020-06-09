package com.schweitzering.data

import com.schweitzering.data.Constants.Companion.FOOD_TYPE
import com.schweitzering.data.Constants.Companion.ONE_DAY_IN_MILLIS
import com.schweitzering.data.Constants.Companion.SALARY_TYPE
import com.schweitzering.domain.categories.TransactionCategory
import com.schweitzering.domain.transaction.Transaction
import com.schweitzering.domain.transaction.TransactionType
import java.sql.Timestamp

class Constants {

    companion object {
        const val SALARY_TYPE = "salary"
        const val FOOD_TYPE = "food"
        const val CLOTHES_TYPE = "clothes"
        const val ONE_DAY_IN_MILLIS = 86400000
    }
}

val transaction1 by lazy {Transaction(
    id = 1,
    value = 500f,
    date = Timestamp(System.currentTimeMillis() - ONE_DAY_IN_MILLIS),
    description = "transaction 1",
    type = TransactionType.INCOME,
    category = categoryIncome,
    accountId = 1
)}

val transaction2 by lazy {Transaction(
    id = 2,
    value = 25f,
    date = Timestamp(System.currentTimeMillis() - ONE_DAY_IN_MILLIS*3),
    description = "transaction 2",
    type = TransactionType.EXPENSE,
    category = categoryExpense,
    accountId = 1
)}

val transaction3 by lazy {Transaction(
    id = 3,
    value = 245f,
    date = Timestamp(System.currentTimeMillis()),
    description = "transaction 3",
    type = TransactionType.EXPENSE,
    category = categoryExpense,
    accountId = 1
)}

val categoryIncome = TransactionCategory(
    id = 1,
    type = TransactionType.INCOME,
    name = SALARY_TYPE
)

val categoryExpense =  TransactionCategory(
    id = 2,
    type = TransactionType.EXPENSE,
    name = FOOD_TYPE
)