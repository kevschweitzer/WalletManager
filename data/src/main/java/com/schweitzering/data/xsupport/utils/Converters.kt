package com.schweitzering.data.xsupport.utils

import androidx.room.TypeConverter
import com.schweitzering.domain.schedule.Period
import com.schweitzering.domain.schedule.TimePeriod
import com.schweitzering.domain.transaction.TransactionCategory
import java.sql.Timestamp

class Converters {

    @TypeConverter
    fun fromTimestamp(value: Timestamp?) = value?.time

    @TypeConverter
    fun toTimestamp(value: Long?) = value?.let{Timestamp(it)}

    @TypeConverter
    fun fromTransactionCategory(category: TransactionCategory) = category.name

    @TypeConverter
    fun toTransactionCategory(name: String) = TransactionCategory.valueOf(name)

    @TypeConverter
    fun fromPeriod(period: TimePeriod) = period.name

    @TypeConverter
    fun toPeriod(name: String) = TimePeriod.valueOf(name)
}