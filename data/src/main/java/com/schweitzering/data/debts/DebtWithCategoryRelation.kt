package com.schweitzering.data.debts

import androidx.room.Embedded
import androidx.room.Relation
import com.schweitzering.data.categories.TransactionCategoryEntity

data class DebtWithCategoryRelation(
    @Embedded val debt: DebtEntity,
    @Relation(
        parentColumn = "categoryId",
        entityColumn = "id"
    )
    val category: TransactionCategoryEntity
)