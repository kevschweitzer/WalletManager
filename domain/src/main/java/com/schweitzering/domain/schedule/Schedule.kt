package com.schweitzering.domain.schedule

import java.sql.Timestamp

data class Schedule(
    var period: TimePeriod,
    var startDate: Timestamp
)

data class Period(var everyXTime: Int, var timePeriod: TimePeriod, var renewalDay: RenewalDays)

enum class TimePeriod {DAY, WEEK, MONTH, YEAR}

enum class RenewalDays {
    MON, TUE, WED, THU, FRI, SAT, SUN, //Applicable to week
    SELF_DAY_REMINDER, //If set 23 of august, every 23 will remind
    MON1, MON2, MON3, MON4, //Applicable to month (first monday of month, second monday of month, etc)
    TUE1, TUE2, TUE3, TUE4,
    WED1, WED2, WED3, WED4,
    THU1, THU2, THU3, THU4,
    FRI1, FRI2, FRI3, FRI4,
    SAT1, SAT2, SAT3, SAT4,
    SUN1, SUN2, SUN3, SUN4
}