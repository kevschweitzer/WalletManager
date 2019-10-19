package com.schweitzering.data.transaction.income

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.schweitzering.domain.transaction.IncomeCategory
import java.sql.Timestamp

@Entity(tableName = "incomes")
data class IncomeEntity (
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "value") var value: Double,
    @ColumnInfo(name = "category") var category: IncomeCategory,
    @ColumnInfo(name = "date") var date: Timestamp
)