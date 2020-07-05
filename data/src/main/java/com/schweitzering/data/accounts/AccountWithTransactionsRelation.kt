package com.schweitzering.data.accounts

import androidx.room.Embedded
import androidx.room.Relation
import com.schweitzering.data.transaction.TransactionEntity

data class AccountWithTransactionsRelation(
    @Embedded val account: AccountEntity,
    @Relation(parentColumn = "account_id", entityColumn = "accountId")
    val transactions: List<TransactionEntity>
)