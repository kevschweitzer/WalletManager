package com.schweitzering.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.schweitzering.data.Constants.Companion.ONE_DAY_IN_MILLIS
import com.schweitzering.data.categories.TransactionCategoryDatabaseManager
import com.schweitzering.data.categories.TransactionCategoryRepositoryImpl
import com.schweitzering.data.transaction.TransactionDatabaseManager
import com.schweitzering.data.transaction.TransactionsRepositoryImpl
import com.schweitzering.data.xsupport.database.AppDatabase
import com.schweitzering.domain.categories.TransactionCategoryRepository
import com.schweitzering.domain.transaction.Transaction
import com.schweitzering.domain.transaction.TransactionsRepository
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.sql.Timestamp


@RunWith(AndroidJUnit4ClassRunner::class)
class TransactionRepositoryTest {

    private lateinit var transactionRepository: TransactionsRepository
    private lateinit var categoryRepository: TransactionCategoryRepository
    private lateinit var appDatabase: AppDatabase

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            AppDatabase::class.java).build()
        transactionRepository = TransactionsRepositoryImpl(TransactionDatabaseManager(appDatabase))
        categoryRepository = TransactionCategoryRepositoryImpl(TransactionCategoryDatabaseManager((appDatabase)))
    }

    @After
    fun closeDb() {
        appDatabase.close()
    }

    @Test
    fun insertTest() {
        categoryRepository.addCategory(transaction1.category)
        transactionRepository.add(transaction1)
        transactionRepository.getAll().observeOnce {
            assertEquals(it, listOf(transaction1))
        }
    }

    @Test
    fun deleteTest() {
        categoryRepository.addCategory(transaction1.category)
        transactionRepository.add(transaction1)
        transactionRepository.remove(transaction1)
        transactionRepository.getAll().observeOnce {
            assertEquals(it, listOf<Transaction>())
        }
    }

    @Test
    fun getAllTest() {
        categoryRepository.addCategory(transaction1.category)
        categoryRepository.addCategory(transaction2.category)
        transactionRepository.add(transaction1)
        transactionRepository.add(transaction2)
        transactionRepository.getAll().observeOnce {
            assertEquals(it, listOf(transaction1, transaction2))
        }
    }

    @Test
    fun getBetweenTest() {
        categoryRepository.addCategory(transaction1.category)
        categoryRepository.addCategory(transaction2.category)
        transactionRepository.add(transaction1)
        transactionRepository.add(transaction2)
        transactionRepository.add(transaction3)
        val twoDaysAgo = Timestamp(System.currentTimeMillis() - ONE_DAY_IN_MILLIS*2)
        val today = Timestamp(System.currentTimeMillis())
        transactionRepository.getBetween(twoDaysAgo, today).observeOnce {
            assertEquals(it, listOf(transaction1, transaction3))
        }
    }
}