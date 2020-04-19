package com.schweitzering.data.utils

import androidx.room.TypeConverter
import com.schweitzering.domain.transaction.TransactionCategory
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
    fun fromTransactionCategory(category: TransactionCategory): String {
        return category.name
    }

    @TypeConverter
    fun toTransactionCategory(name: String): TransactionCategory {
        return TransactionCategory.valueOf(name)
    }
}