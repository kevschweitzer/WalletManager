package com.schweitzering.data.fixedExpenses

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.schweitzering.domain.transaction.Transaction
import java.sql.Timestamp

@Entity(tableName = "fixed_expenses")
data class FixedExpenseEntity (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "fixed_expense_id") var id: Int = 0,
    @Embedded var expense: Transaction,
    var isAlreadyPaid: Boolean = false, //In the current period
    var creationDate: Timestamp,
    var paymentDate: Timestamp? = null
)