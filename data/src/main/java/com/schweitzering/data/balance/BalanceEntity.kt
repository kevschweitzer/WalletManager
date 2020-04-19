package com.schweitzering.data.balance

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

/**
 * Using unique id to force balance to be only one entry that keep current balance. It should update
 * with every transaction.
*/
@Entity(tableName = "balances")
data class BalanceEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var value: Float,
    var date: Timestamp
)