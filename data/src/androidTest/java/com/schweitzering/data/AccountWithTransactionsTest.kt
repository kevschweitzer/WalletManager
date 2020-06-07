package com.schweitzering.data

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.schweitzering.data.accounts.AccountRepositoryImpl
import com.schweitzering.data.categories.TransactionCategoryDatabaseManager
import com.schweitzering.data.categories.TransactionCategoryRepositoryImpl
import com.schweitzering.data.transaction.TransactionDatabaseManager
import com.schweitzering.data.transaction.TransactionsRepositoryImpl
import com.schweitzering.data.xsupport.database.AppDatabase
import com.schweitzering.domain.accounts.Account
import com.schweitzering.domain.accounts.AccountRepository
import com.schweitzering.domain.accounts.AccountWithTransactions
import com.schweitzering.domain.categories.TransactionCategory
import com.schweitzering.domain.categories.TransactionCategoryRepository
import com.schweitzering.domain.transaction.Transaction
import com.schweitzering.domain.transaction.TransactionType
import com.schweitzering.domain.transaction.TransactionsRepository
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.sql.Timestamp

@RunWith(AndroidJUnit4ClassRunner::class)
class AccountWithTransactionsTest {

    private lateinit var accountRepository: AccountRepository
    private lateinit var transactionRepository: TransactionsRepository
    private lateinit var categoryRepository: TransactionCategoryRepository

    private lateinit var appDatabase: AppDatabase

    private lateinit var context: Context

    private val category1 = TransactionCategory(
        id = 1,
        name = "category1",
        type = TransactionType.EXPENSE
    )

    private val account1 = Account(
        id = 1,
        name = "account 1",
        description = "Some description"
    )

    private val transaction1 by lazy { Transaction(
        category = category1,
        date = Timestamp(System.currentTimeMillis()),
        description = "one description",
        type = TransactionType.INCOME,
        value = 50f,
        accountId = account1.id
    )}


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().context
        appDatabase = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            AppDatabase::class.java).build()
        accountRepository = AccountRepositoryImpl(appDatabase)
        transactionRepository = TransactionsRepositoryImpl(TransactionDatabaseManager(appDatabase))
        categoryRepository = TransactionCategoryRepositoryImpl(TransactionCategoryDatabaseManager(appDatabase))
    }

    @After
    fun close() {
        appDatabase.close()
    }

    @Test
    fun getAccountWithTransactionsTest() {
        categoryRepository.addCategory(category1)
        accountRepository.add(account1)
        transactionRepository.add(transaction1)
        accountRepository.getAccountsWithTransactions().observeOnce {
            assertEquals(it, listOf(AccountWithTransactions(account1, listOf(transaction1))))
        }
    }
}