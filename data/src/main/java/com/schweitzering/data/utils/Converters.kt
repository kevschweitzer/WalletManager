package com.schweitzering.data.utils

import androidx.room.TypeConverter
import com.schweitzering.domain.finance.FinanceCategory
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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
    fun fromFinanceCategory(category: FinanceCategory): String {
        return category.name
    }

    @TypeConverter
    fun toFinanceCategory(name: String): FinanceCategory {
        return FinanceCategory.valueOf(name)
    }
}