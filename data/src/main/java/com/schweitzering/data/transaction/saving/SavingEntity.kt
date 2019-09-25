package com.schweitzering.data.transaction.saving

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.schweitzering.domain.transaction.SavingCategory
import java.sql.Timestamp

@Entity
data class SavingEntity (
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "value") var value: Int,
    @ColumnInfo(name = "category") var category: SavingCategory,
    @ColumnInfo(name = "date") var date: Timestamp
)