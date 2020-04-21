package com.schweitzering.data.fixedExpenses

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.schweitzering.domain.fixedExpenses.RenewalDays
import com.schweitzering.domain.fixedExpenses.TimePeriod
import com.schweitzering.domain.transaction.TransactionCategory
import java.sql.Timestamp

@Entity(tableName = "fixed_expenses")
data class FixedExpenseEntity (
    @PrimaryKey(autoGenerate = true) var id: Int,
    var value: Float,
    var date: Timestamp,
    var category: TransactionCategory, //Each Transaction has a category
    var categoryType: String,
    var isAlreadyPaid: Boolean, //In the current period
    var everyXTime: Int,
    var timePeriod: TimePeriod,
    var renewalDay: RenewalDays,
    var startDate: Timestamp,
    var creationDate: Timestamp
)