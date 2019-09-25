package com.schweitzering.data.transaction

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.schweitzering.domain.transaction.ExpenseCategory
import java.sql.Timestamp

@Entity
data class ExpenseEntity (
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "value") var value: Int,
    @ColumnInfo(name = "category") var category: ExpenseCategory,
    @ColumnInfo(name = "date") var date: Timestamp
)