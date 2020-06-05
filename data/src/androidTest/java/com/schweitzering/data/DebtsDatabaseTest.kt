package com.schweitzering.data

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.schweitzering.data.debts.DebtEntity
import com.schweitzering.data.debts.DebtsDatabaseManager
import com.schweitzering.data.transaction.TransactionEntity
import com.schweitzering.data.xsupport.database.AppDatabase
import com.schweitzering.domain.transaction.TransactionType
import org.junit.*
import org.junit.Assert.assertEquals
import org.junit.runner.RunWith
import java.sql.Timestamp

@RunWith(AndroidJUnit4ClassRunner::class)
class DebtsDatabaseTest {

    private lateinit var databaseManager: DebtsDatabaseManager

    private lateinit var appDatabase: AppDatabase

    private lateinit var context: Context

    private val entity1 = DebtEntity(
        id = 1,
        transaction = TransactionEntity(
            categoryId = 0,
            date = Timestamp(System.currentTimeMillis()),
            description = "",
            type = TransactionType.SAVING,
            value = 50f
        ),
        creationDate = Timestamp(System.currentTimeMillis())
    )

    private val entity2 = DebtEntity(
        id = 2,
        transaction = TransactionEntity(
            categoryId = 0,
            date = Timestamp(System.currentTimeMillis()),
            description = "other description",
            type = TransactionType.INVESTMENT,
            value = 500f
        ),
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
        databaseManager = DebtsDatabaseManager(appDatabase)
    }

    @After
    fun close() {
        appDatabase.close()
    }

    @Test
    fun insertDebtTest() {
        databaseManager.insert(entity1)
        databaseManager.getAll().observeOnce {
            assertEquals(it, listOf(entity1))
        }
    }

    @Test
    fun deleteDebtTest() {
        databaseManager.insert(entity1)
        databaseManager.delete(entity1)
        databaseManager.getAll().observeOnce {
            assertEquals(it, listOf<DebtEntity>())
        }
    }

    @Test
    fun getAllTest() {
        databaseManager.insert(entity1)
        databaseManager.insert(entity2)
        databaseManager.getAll().observeOnce {
            assertEquals(it, listOf(entity1, entity2))
        }
    }
}