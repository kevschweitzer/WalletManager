package com.schweitzering.data.xsupport.utils

import androidx.room.TypeConverter
import com.schweitzering.domain.fixedExpenses.RenewalDays
import com.schweitzering.domain.fixedExpenses.TimePeriod
import com.schweitzering.domain.transaction.TransactionCategory
import java.sql.Timestamp

class Converters {

    @TypeConverter
    fun fromTimestamp(value: Timestamp) = value.time

    @TypeConverter
    fun toTimestamp(value: Long) = Timestamp(value)

    @TypeConverter
    fun fromTransactionCategory(category: TransactionCategory) = category.name


    @TypeConverter
    fun toTransactionCategory(name: String) = TransactionCategory.valueOf(name)

    @TypeConverter
    fun toTimePeriod(name: String) = TimePeriod.valueOf(name)

    @TypeConverter
    fun fromTimePeriod(timePeriod: TimePeriod) = timePeriod.name

    @TypeConverter
    fun toRenewalDay(name: String) = RenewalDays.valueOf(name)

    @TypeConverter
    fun fromRenewalDays(renewalDays: RenewalDays) = renewalDays.name
}