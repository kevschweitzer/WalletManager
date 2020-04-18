package com.schweitzering.data

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.schweitzering.data.Constants.Companion.SALARY_TYPE
import com.schweitzering.data.categorytypes.CategoryTypeEntity
import com.schweitzering.data.categorytypes.CategoryTypesDatabaseManager
import com.schweitzering.data.database.AppDatabase
import com.schweitzering.domain.transaction.TransactionCategory
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class CategoryTypesDatabaseManagerTest {

    private lateinit var databaseManager: CategoryTypesDatabaseManager

    private lateinit var appDatabase: AppDatabase

    private lateinit var context: Context

    private val baseExpenseCategories: MutableList<String> by lazy { context.resources.getStringArray(R.array.expense_categories).toMutableList() }

    val entity1 = CategoryTypeEntity(
        id = 1,
        category = TransactionCategory.EXPENSE,
        type = "cinema"
    )

    val entity2 = CategoryTypeEntity(
        id = 2,
        category = TransactionCategory.EXPENSE,
        type = "gym"
    )

    val entity3 = CategoryTypeEntity(
        id = 3,
        category = TransactionCategory.INCOME,
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
        databaseManager = CategoryTypesDatabaseManager(appDatabase)
    }

    @After
    fun close() {
        appDatabase.close()
    }

    @Test fun addCategoryTypeTest() {
        databaseManager.add(entity1)
        databaseManager.getAllByCategory(TransactionCategory.EXPENSE).observeOnce {
            assertEquals(it, listOf(entity1.type))
        }
    }

    @Test fun addTwoCategoryTypeTest() {
        databaseManager.add(entity1)
        databaseManager.add(entity2)
        databaseManager.getAllByCategory(TransactionCategory.EXPENSE).observeOnce {
            assertEquals(it, listOf(entity1.type, entity2.type))
        }
    }

    @Test fun getAllByCategoryTypeTest() {
        databaseManager.add(entity1)
        databaseManager.add(entity2)
        databaseManager.add(entity3)

        databaseManager.getAllByCategory(TransactionCategory.EXPENSE).observeOnce {
            assertEquals(it, listOf(entity1.type, entity2.type))
        }
    }

    @Test fun deleteCategoryTypeTest() {
        databaseManager.add(entity1)
        databaseManager.remove(entity1)

        databaseManager.getAllByCategory(TransactionCategory.EXPENSE).observeOnce {
            assertEquals(it, listOf<String>())
        }
    }

}