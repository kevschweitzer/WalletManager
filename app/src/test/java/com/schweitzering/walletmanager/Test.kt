package com.schweitzering.walletmanager

import java.sql.Timestamp
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*


fun main() {
    println(Date(Timestamp(System.currentTimeMillis()).time))
    val localDate = Date(Timestamp(System.currentTimeMillis()).time).toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
    print(localDate.toString())
}

private fun toDate(timestamp: Long): String {
    val date = Instant.ofEpochMilli(timestamp * 1000).atZone(ZoneId.systemDefault()).toLocalDate()
    return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
}