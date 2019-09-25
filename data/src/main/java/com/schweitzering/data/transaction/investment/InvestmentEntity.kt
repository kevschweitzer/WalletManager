package com.schweitzering.data.transaction.investment

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.schweitzering.domain.transaction.InvestmentCategory
import java.sql.Timestamp

@Entity
data class InvestmentEntity (
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "value") var value: Int,
    @ColumnInfo(name = "category") var category: InvestmentCategory,
    @ColumnInfo(name = "date") var date: Timestamp
)
