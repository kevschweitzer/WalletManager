package com.schweitzering.data.debts

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.schweitzering.domain.transaction.TransactionCategory
import java.sql.Timestamp

@Entity(tableName = "debts")
data class DebtEntity (
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var value: Float,
    var category: TransactionCategory, //Each Transaction has a category
    var categoryType: String,
    var creationDate: Timestamp,
    var isResolved: Boolean = false, //Paid or charged regarding category (expense, income)
    var resolveDate: Timestamp? = null //Date to pay/charge this debt
)