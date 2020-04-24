package com.schweitzering.data

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.schweitzering.data.fixedExpenses.FixedExpenseEntity
import com.schweitzering.data.fixedExpenses.FixedExpensesDatabaseManager
import com.schweitzering.data.xsupport.database.AppDatabase
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

    val entity1 = FixedExpenseEntity(
        id = 1,
        value = 300f,
        category = TransactionCategory.EXPENSE, //Each Transaction has a category
        categoryType = Constants.FOOD_TYPE,
        creationDate = Timestamp(System.currentTimeMillis())
    )

    val entity2 = FixedExpenseEntity(
        id = 2,
        value = 150f,
        category = TransactionCategory.EXPENSE, //Each Transaction has a category
        categoryType = Constants.CLOTHES_TYPE,
        creationDate = Timestamp(System.currentTimeMillis())
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