package com.schweitzering.data.debts

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.schweitzering.domain.transaction.Transaction
import java.sql.Timestamp

@Entity(tableName = "debts")
data class DebtEntity (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "debt_id")
    var id: Int? = 0,
    @Embedded val transaction: Transaction,
    var creationDate: Timestamp,
    var isResolved: Boolean = false, //Paid or charged regarding category (expense, income)
    var resolveDate: Timestamp? = null //Date to pay/charge this debt
)