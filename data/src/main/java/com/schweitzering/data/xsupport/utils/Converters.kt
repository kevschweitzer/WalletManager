package com.schweitzering.data.xsupport.utils

import androidx.room.TypeConverter
import com.schweitzering.domain.categories.TransactionCategory
import com.schweitzering.domain.schedule.TimePeriod
import com.schweitzering.domain.transaction.TransactionType
import java.sql.Timestamp

class Converters {

    @TypeConverter
    fun fromTimestamp(value: Timestamp?) = value?.time

    @TypeConverter
    fun toTimestamp(value: Long?) = value?.let{Timestamp(it)}

   /* @TypeConverter
    fun fromTransactionType(type: TransactionType) = type.name

    @TypeConverter
    fun toTransactionType(name: String) = TransactionType.valueOf(name)

    @TypeConverter
    fun fromTransactionCategory(category: TransactionCategory) {}

    @TypeConverter
    fun toTransactionCategory(id: Int) {}*/

    @TypeConverter
    fun fromPeriod(period: TimePeriod) = period.name

    @TypeConverter
    fun toPeriod(name: String) = TimePeriod.valueOf(name)
}