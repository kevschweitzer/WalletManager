package com.schweitzering.data.fixedExpenses

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.schweitzering.domain.schedule.Schedule
import com.schweitzering.domain.transaction.TransactionCategory
import java.sql.Timestamp

@Entity(tableName = "fixed_expenses")
data class FixedExpenseEntity (
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var value: Float,
    var date: Timestamp,
    var category: TransactionCategory, //Each Transaction has a category
    var categoryType: String,
    var isAlreadyPaid: Boolean, //In the current period
    var creationDate: Timestamp,
    @Embedded val schedule: Schedule
)