package com.schweitzering.data.accounts

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "accounts")
data class AccountEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String,
    var description: String
)