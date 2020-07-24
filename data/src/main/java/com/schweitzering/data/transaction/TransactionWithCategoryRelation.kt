package com.schweitzering.data.transaction

import androidx.room.Embedded
import androidx.room.Relation
import com.schweitzering.data.accounts.AccountEntity
import com.schweitzering.data.categories.TransactionCategoryEntity

data class TransactionWithCategoryRelation(
    @Embedded val transaction: TransactionEntity,
    @Relation(
        parentColumn = "categoryId",
        entityColumn = "id"
    )
    val category: TransactionCategoryEntity,
    @Relation(
        parentColumn = "accountId",
        entityColumn = "account_id"
    )
    val account: AccountEntity
)