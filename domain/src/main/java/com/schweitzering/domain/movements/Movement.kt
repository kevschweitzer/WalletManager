package com.schweitzering.domain.movements

import java.sql.Timestamp

abstract class Movement(
    var value: Float,
    var description: String,
    var date: Timestamp? = null
)