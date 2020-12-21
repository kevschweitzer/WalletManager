package com.schweitzering.data.debts

import androidx.room.*
import com.schweitzering.data.accounts.AccountEntity
import com.schweitzering.data.categories.TransactionCategoryEntity
import com.schweitzering.data.transaction.TransactionEntity
import com.schweitzering.domain.categories.TransactionCategory
import com.schweitzering.domain.transaction.Transaction
import com.schweitzering.domain.transaction.TransactionType
import java.sql.Timestamp

@Entity(tableName = "debts",
    foreignKeys = [
        ForeignKey(
            entity = TransactionCategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.RESTRICT)
    ]
)

data class DebtEntity (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "debt_id")
    var id: Int = 0,
    var value: Float,
    var description: String,
    var categoryId: Int,
    var creationDate: Timestamp,
    var type: TransactionType,
    var isResolved: Boolean = false, //Paid or charged regarding category (expense, income)
    var resolveDate: Timestamp? = null //Date to pay/charge this debt
)