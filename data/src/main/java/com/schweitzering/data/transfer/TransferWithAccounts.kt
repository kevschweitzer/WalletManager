package com.schweitzering.data.transfer

import androidx.room.Embedded
import androidx.room.Relation
import com.schweitzering.data.accounts.AccountEntity

class TransferWithAccounts(
    @Embedded val transfer: TransferEntity,
    @Relation(
        parentColumn = "originAccountId",
        entityColumn = "id"
    )
    val originAccount: AccountEntity,
    @Relation(
        parentColumn = "destinationAccountId",
        entityColumn = "id"
    )
    val destinationAccount: AccountEntity
)