package com.schweitzering.data.schedule

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.schweitzering.domain.schedule.TimePeriod
import java.sql.Timestamp

@Entity(tableName = "schedules")
data class ScheduleEntity(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var period: TimePeriod,
    var startDate: Timestamp
)