package com.schweitzering.domain.tranfer

import com.schweitzering.domain.accounts.Account
import java.sql.Timestamp

data class Transfer(
    var id: Int,
    var value: Float,
    var description: String,
    var date: Timestamp,
    var originAccount: Account,
    var destinationAccount: Account
)