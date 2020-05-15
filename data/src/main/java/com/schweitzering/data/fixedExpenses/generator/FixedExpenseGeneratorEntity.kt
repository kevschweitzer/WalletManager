package com.schweitzering.data.fixedExpenses.generator

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.schweitzering.domain.schedule.Schedule
import com.schweitzering.domain.transaction.Transaction
import java.sql.Timestamp

@Entity(tableName = "fixed_expenses_generators")
class FixedExpenseGeneratorEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "generator_id")
    var id: Int,
    @Embedded var expense: Transaction,
    var creationDate: Timestamp,
    @Embedded var schedule: Schedule
)