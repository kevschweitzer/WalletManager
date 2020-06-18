package com.schweitzering.data.accounts

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "accounts")
data class AccountEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "account_id")
    var id: Int,
    var name: String,
    var description: String,
    var balance: Float
) {
    companion object {
        fun getPredefinedAccount(): AccountEntity {
            return AccountEntity(
                id = 0,
                name = "Wallet",
                description = "Cash",
                balance = 0f
            )
        }
    }
}