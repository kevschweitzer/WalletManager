package com.schweitzering.data

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.schweitzering.data.fixedExpenses.FixedExpenseEntity
import com.schweitzering.data.fixedExpenses.FixedExpensesDatabaseManager
import com.schweitzering.data.xsupport.database.AppDatabase
import com.schweitzering.domain.schedule.Schedule
import com.schweitzering.domain.schedule.TimePeriod
import com.schweitzering.domain.transaction.TransactionCategory
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.sql.Timestamp

@RunWith(AndroidJUnit4ClassRunner::class)
class FixedExpensesTest {

    private lateinit var databaseManager: FixedExpensesDatabaseManager

    private lateinit var appDatabase: AppDatabase

    private lateinit var context: Context

    val entity1 by lazy{ FixedExpenseEntity(
        id = 1,
        value = 300f,
        date = Timestamp(System.currentTimeMillis()),
        category = TransactionCategory.EXPENSE, //Each Transaction has a category
        categoryType = Constants.FOOD_TYPE,
        isAlreadyPaid = false, //In the current period
        creationDate = Timestamp(System.currentTimeMillis()),
        schedule = schedule1
    )}

    val entity2 by lazy { FixedExpenseEntity(
        id = 2,
        value = 150f,
        date = Timestamp(System.currentTimeMillis()),
        category = TransactionCategory.EXPENSE, //Each Transaction has a category
        categoryType = Constants.CLOTHES_TYPE,
        isAlreadyPaid = false, //In the current period
        creationDate = Timestamp(System.currentTimeMillis()),
        schedule = schedule2
    )}

    val schedule1 = Schedule(
        period = TimePeriod.MONTH,
        startDate = Timestamp(System.currentTimeMillis())
    )

    val schedule2 = Schedule(
        period = TimePeriod.WEEK,
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
        databaseManager = FixedExpensesDatabaseManager(appDatabase)
    }

    @After
    fun close() {
        appDatabase.close()
    }

    @Test fun addFixedExpenseTest() {
        databaseManager.add(entity1)
        databaseManager.getAll().observeOnce {
            assertEquals(it, listOf(entity1))
        }
    }

    @Test fun removeFixedExpenseTest() {
        databaseManager.add(entity1)
        databaseManager.remove(entity1)
        databaseManager.getAll().observeOnce {
            assertEquals(it, listOf<FixedExpenseEntity>())
        }
    }

    @Test fun getAllFixedExpensesTest() {
        databaseManager.add(entity1)
        databaseManager.add(entity2)
        databaseManager.getAll().observeOnce {
            assertEquals(it, listOf(entity1, entity2))
        }
    }
}