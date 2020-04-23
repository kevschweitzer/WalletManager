package com.schweitzering.data.schedule

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ScheduleDao {

    @Insert
    suspend fun insert(schedule: ScheduleEntity)

    @Delete
    suspend fun delete(schedule: ScheduleEntity)

    @Query("SELECT * FROM schedules")
    fun getAll(): LiveData<List<ScheduleEntity>>
}