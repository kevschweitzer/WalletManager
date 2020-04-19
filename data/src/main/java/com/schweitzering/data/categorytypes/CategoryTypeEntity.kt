package com.schweitzering.data.categorytypes

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.schweitzering.domain.transaction.TransactionCategory

@Entity(tableName = "category_types")
data class CategoryTypeEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var category: TransactionCategory,
    var type: String
)