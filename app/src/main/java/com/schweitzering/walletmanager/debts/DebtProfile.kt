package com.schweitzering.walletmanager.debts

import com.schweitzering.domain.transaction.Transaction
import com.schweitzering.domain.transaction.TransactionCategory
import java.sql.Timestamp

data class DebtProfile(
    var id: Int? = null,
    var transaction: Transaction,
    var creationDate: Timestamp,
    var isResolved: Boolean = false, //Paid or charged regarding category (expense, income)
    var resolveDate: Timestamp? = null //Date to pay/charge this debt
)