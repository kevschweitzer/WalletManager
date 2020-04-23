package com.schweitzering.data.schedule

import com.schweitzering.data.xsupport.database.AppDatabase
import kotlinx.coroutines.runBlocking

class ScheduleDatabaseManager(private val appDatabase: AppDatabase) {

    private val scheduleDao = appDatabase.scheduleDao()

    fun add(schedule: ScheduleEntity) {
        runBlocking {
            scheduleDao.insert(schedule)
        }
    }

    fun delete(schedule: ScheduleEntity) {
        runBlocking {
            scheduleDao.delete(schedule)
        }
    }

    fun getAll() = scheduleDao.getAll()
}