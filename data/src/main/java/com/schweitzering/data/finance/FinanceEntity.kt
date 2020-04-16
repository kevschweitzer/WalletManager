package com.schweitzering.data.finance

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.schweitzering.domain.finance.Finance
import com.schweitzering.domain.finance.FinanceCategory
import java.sql.Timestamp
import java.time.LocalDate
import java.time.LocalDateTime


@Entity(tableName = "finances")
data class FinanceEntity (
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var value: Float,
    var date: Timestamp,
    var category: FinanceCategory,
    var financeCategoryType: String
)