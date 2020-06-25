package com.schweitzering.data.transfer

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "transfers")
data class TransferEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var value: Float,
    var description: String,
    var date: Timestamp,
    var originAccountId: Int,
    var destinationAccountId: Int
)