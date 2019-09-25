package com.schweitzering.data.transaction

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.schweitzering.domain.transaction.IncomeCategory
import java.sql.Timestamp

@Entity
data class IncomeEntity (
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "value") var value: Int,
    @ColumnInfo(name = "category") var category: IncomeCategory,
    @ColumnInfo(name = "date") var date: Timestamp
)