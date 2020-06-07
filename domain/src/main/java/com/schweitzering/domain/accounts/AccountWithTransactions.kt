package com.schweitzering.domain.accounts

import com.schweitzering.domain.transaction.Transaction

data class AccountWithTransactions(
    val account: Account,
    val transactions: List<Transaction>
)