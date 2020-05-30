package com.schweitzering.data.transaction

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.schweitzering.domain.categories.TransactionCategory
import com.schweitzering.domain.transaction.TransactionType
import java.sql.Timestamp


@Entity(tableName = "transactions")
data class TransactionEntity (
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var value: Float,
    var date: Timestamp?,
    var description: String,
    var type: TransactionType,
    var category: TransactionCategory
)