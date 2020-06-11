package com.schweitzering.domain.accounts

import com.schweitzering.domain.transaction.Transaction

//TODO: Add currency
data class Account(
    var id: Int = 0,
    var name: String,
    var description: String,
    var balance: Float = 0f,
    var transactions: MutableList<Transaction> = mutableListOf()
)