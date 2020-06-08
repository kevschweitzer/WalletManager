package com.schweitzering.data.transaction

import androidx.room.Embedded
import androidx.room.Relation
import com.schweitzering.data.categories.TransactionCategoryEntity

data class TransactionWithCategoryRelation(
    @Embedded val transaction: TransactionEntity,
    @Relation(
        parentColumn = "categoryId",
        entityColumn = "id"
    )
    val category: TransactionCategoryEntity
)