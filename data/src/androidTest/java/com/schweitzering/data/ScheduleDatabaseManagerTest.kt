package com.schweitzering.data

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.schweitzering.data.schedule.ScheduleDatabaseManager
import com.schweitzering.data.schedule.ScheduleEntity
import com.schweitzering.data.xsupport.database.AppDatabase
import com.schweitzering.domain.schedule.TimePeriod
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.sql.Timestamp

@RunWith(AndroidJUnit4ClassRunner::class)
class ScheduleDatabaseManagerTest {

    private lateinit var databaseManager: ScheduleDatabaseManager

    private lateinit var appDatabase: AppDatabase

    private lateinit var context: Context

    private val entity = ScheduleEntity(
        id = 1,
        period = TimePeriod.MONTH,
        startDate = Timestamp(System.currentTimeMillis())
    )

    private val entity2 = ScheduleEntity(
        id = 2,
        period = TimePeriod.MONTH,
        startDate = Timestamp(System.currentTimeMillis())
    )

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().context
        appDatabase = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            AppDatabase::class.java).build()
        databaseManager = ScheduleDatabaseManager(appDatabase)
    }

    @After
    fun close() {
        appDatabase.close()
    }

    @Test fun addScheduleTest() {
        databaseManager.add(entity)
        databaseManager.getAll().observeOnce {
            assertEquals(it, listOf(entity))
        }
    }

    @Test fun deleteScheduleTest() {
        databaseManager.add(entity)
        databaseManager.delete(entity)
        databaseManager.getAll().observeOnce {
            assertEquals(it, listOf<ScheduleEntity>())
        }
    }

    @Test fun getAllTest() {
        databaseManager.add(entity)
        databaseManager.add(entity2)
        databaseManager.getAll().observeOnce {
            assertEquals(it, listOf(entity, entity2))
        }
    }

}