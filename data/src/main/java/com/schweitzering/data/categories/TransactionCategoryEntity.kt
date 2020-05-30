package com.schweitzering.data.categories

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.schweitzering.domain.transaction.TransactionType

@Entity(tableName = "transaction_categories")
data class TransactionCategoryEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var type: TransactionType,
    var name: String
)