package com.schweitzering.data

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.schweitzering.data.categories.TransactionCategoryEntity
import com.schweitzering.data.categories.TransactionCategoryDatabaseManager
import com.schweitzering.data.xsupport.database.AppDatabase
import com.schweitzering.domain.transaction.TransactionType
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class TransactionTransactionCategoryDatabaseManagerTest {

    private lateinit var databaseManagerTransaction: TransactionCategoryDatabaseManager

    private lateinit var appDatabase: AppDatabase

    private lateinit var context: Context

    private val baseExpenseCategories: MutableList<String> by lazy { context.resources.getStringArray(R.array.expense_categories).toMutableList() }

    val entity1 = TransactionCategoryEntity(
        id = 1,
        category = TransactionType.EXPENSE,
        type = "cinema"
    )

    val entity2 = TransactionCategoryEntity(
        id = 2,
        category = TransactionType.EXPENSE,
        type = "gym"
    )

    val entity3 = TransactionCategoryEntity(
        id = 3,
        category = TransactionType.INCOME,
        type = "sale"
    )

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().context
        appDatabase = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            AppDatabase::class.java).build()
        databaseManagerTransaction = TransactionCategoryDatabaseManager(appDatabase)
    }

    @After
    fun close() {
        appDatabase.close()
    }

    @Test fun addCategoryTypeTest() {
        databaseManagerTransaction.add(entity1)
        databaseManagerTransaction.getAllByCategory(TransactionType.EXPENSE).observeOnce {
            assertEquals(it, listOf(entity1.type))
        }
    }

    @Test fun addTwoCategoryTypeTest() {
        databaseManagerTransaction.add(entity1)
        databaseManagerTransaction.add(entity2)
        databaseManagerTransaction.getAllByCategory(TransactionType.EXPENSE).observeOnce {
            assertEquals(it, listOf(entity1.type, entity2.type))
        }
    }

    @Test fun getAllByCategoryTypeTest() {
        databaseManagerTransaction.add(entity1)
        databaseManagerTransaction.add(entity2)
        databaseManagerTransaction.add(entity3)

        databaseManagerTransaction.getAllByCategory(TransactionType.EXPENSE).observeOnce {
            assertEquals(it, listOf(entity1.type, entity2.type))
        }
    }

    @Test fun deleteCategoryTypeTest() {
        databaseManagerTransaction.add(entity1)
        databaseManagerTransaction.remove(entity1)

        databaseManagerTransaction.getAllByCategory(TransactionType.EXPENSE).observeOnce {
            assertEquals(it, listOf<String>())
        }
    }

}