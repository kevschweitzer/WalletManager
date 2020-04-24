package com.schweitzering.data.fixedExpenses.generator

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.schweitzering.domain.schedule.Schedule
import java.sql.Timestamp

@Entity(tableName = "fixed_expenses_generators")
class FixedExpenseGeneratorEntity(
    @PrimaryKey
    var id: Int,
    var value: Float,
    var categoryType: String,
    var creationDate: Timestamp,
    @Embedded var schedule: Schedule
)