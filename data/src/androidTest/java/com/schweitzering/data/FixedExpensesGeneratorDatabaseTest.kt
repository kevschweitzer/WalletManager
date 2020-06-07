package com.schweitzering.data

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.schweitzering.data.Constants.Companion.CLOTHES_TYPE
import com.schweitzering.data.Constants.Companion.FOOD_TYPE
import com.schweitzering.data.fixedExpenses.generator.FixedExpenseGeneratorEntity
import com.schweitzering.data.fixedExpenses.generator.FixedExpensesGeneratorDatabaseManager
import com.schweitzering.data.transaction.TransactionEntity
import com.schweitzering.data.xsupport.database.AppDatabase
import com.schweitzering.domain.schedule.Schedule
import com.schweitzering.domain.schedule.TimePeriod
import com.schweitzering.domain.transaction.TransactionType
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.*
import org.junit.runner.RunWith
import java.sql.Timestamp

@RunWith(AndroidJUnit4ClassRunner::class)
class FixedExpensesGeneratorDatabaseTest {

    private lateinit var databaseManager: FixedExpensesGeneratorDatabaseManager

    private lateinit var appDatabase: AppDatabase

    private lateinit var context: Context

    val entity1 by lazy { FixedExpenseGeneratorEntity(
        id = 1,
        expense = TransactionEntity(
            categoryId = 0,
            date = Timestamp(System.currentTimeMillis()),
            description = "other description",
            type = TransactionType.EXPENSE,
            value = 500f,
            accountId = 1
        ),
        creationDate = Timestamp(System.currentTimeMillis()),
        schedule = schedule1
    )}

    val entity2 by lazy { FixedExpenseGeneratorEntity(
        id = 2,
        expense = TransactionEntity(
            categoryId = 0,
            date = Timestamp(System.currentTimeMillis()),
            description = "other description",
            type = TransactionType.EXPENSE,
            value = 500f,
            accountId = 1
        ),
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
        databaseManager = FixedExpensesGeneratorDatabaseManager(appDatabase)
    }

    @After
    fun close() {
        appDatabase.close()
    }

    @Test
    fun insertTest() {
        databaseManager.insert(entity1)
//        databaseManager.getAll().observeOnce {
//            assertTrue(it[0].id == entity1.id)
//            assertTrue(it[0].categoryType == entity1.categoryType)
//            assertTrue(it[0].value == entity1.value)
//            assertTrue(it[0].schedule == entity1.schedule)
//            assertTrue(it[0].creationDate == entity1.creationDate)
//        }
    }

    @Test
    fun deleteTest(){
//        databaseManager.insert(entity1)
//        databaseManager.delete(entity1)
//        databaseManager.getAll().observeOnce {
//            assertEquals(it, listOf<FixedExpenseGeneratorEntity>())
//        }
    }

    @Test
    fun getAllTest() {
//        databaseManager.insert(entity1)
//        databaseManager.insert(entity2)
//        databaseManager.getAll().observeOnce {
//            assertTrue(it.size == 2)
//        }
    }

}