package com.schweitzering.data.transaction

import androidx.room.TypeConverter
import com.schweitzering.domain.transaction.ExpenseCategory
import com.schweitzering.domain.transaction.IncomeCategory
import java.sql.Timestamp

class Converters {

    @TypeConverter
    fun fromTimestamp(value: Timestamp): Long? {
        return value.time
    }

    @TypeConverter
    fun toTimestamp(value: Long): Timestamp {
        return Timestamp(value)
    }

    @TypeConverter
    fun fromExpenseCategory(category: ExpenseCategory): String {
        return category.name
    }

    @TypeConverter
    fun toExpenseCategory(name: String): ExpenseCategory {
        return ExpenseCategory.valueOf(name)
    }

    @TypeConverter
    fun fromIncomeCategory(category: IncomeCategory): String {
        return category.name
    }

    @TypeConverter
    fun toIncomeCategory(name: String): IncomeCategory {
        return IncomeCategory.valueOf(name)
    }
}