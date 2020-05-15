package com.schweitzering.data.transaction

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.schweitzering.domain.transaction.TransactionCategory
import java.sql.Timestamp


@Entity(tableName = "transactions")
data class TransactionEntity (
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var value: Float,
    var date: Timestamp?,
    var description: String,
    var category: TransactionCategory,
    var categoryType: String
)