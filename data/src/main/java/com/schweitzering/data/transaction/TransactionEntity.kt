package com.schweitzering.data.transaction

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.schweitzering.data.accounts.AccountEntity
import com.schweitzering.data.categories.TransactionCategoryEntity
import com.schweitzering.domain.transaction.TransactionType
import java.sql.Timestamp

@Entity(tableName = "transactions",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = AccountEntity::class,
            parentColumns = ["account_id"],
            childColumns = ["accountId"],
            onDelete = ForeignKey.RESTRICT
        ),
        ForeignKey(
            entity = TransactionCategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.RESTRICT
        )
    )
)
data class TransactionEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var value: Float,
    var date: Timestamp?,
    var description: String,
    var type: TransactionType,
    var categoryId: Int,
    var accountId: Int
)