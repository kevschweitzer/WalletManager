package com.schweitzering.domain.tranfer

import com.schweitzering.domain.accounts.Account
import com.schweitzering.domain.movements.Movement
import java.sql.Timestamp

class Transfer(
    var id: Int = 0,
    value: Float,
    description: String,
    date: Timestamp?,
    var originAccount: Account,
    var destinationAccount: Account
): Movement(value, description, date)