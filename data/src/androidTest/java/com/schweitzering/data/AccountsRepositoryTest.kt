package com.schweitzering.data

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.schweitzering.account1
import com.schweitzering.account2
import com.schweitzering.data.accounts.AccountRepositoryImpl
import com.schweitzering.data.xsupport.database.AppDatabase
import com.schweitzering.domain.accounts.Account
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class AccountsRepositoryTest {

    private lateinit var accountRepository: AccountRepositoryImpl

    private lateinit var appDatabase: AppDatabase

    private lateinit var context: Context

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().context
        appDatabase = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            AppDatabase::class.java).build()
        accountRepository = AccountRepositoryImpl(appDatabase)
    }

    @After
    fun close() {
        appDatabase.close()
    }

    @Test
    fun insertTest() {
        accountRepository.add(account1)
        accountRepository.getAll().observeOnce {
            assertEquals(it, listOf(account1))
        }
    }

    @Test
    fun deleteTest() {
        accountRepository.add(account1)
        accountRepository.remove(account1)
        accountRepository.getAll().observeOnce {
            assertEquals(it, listOf<Account>())
        }
    }

    @Test
    fun updateTest() {
        accountRepository.add(account1)
        val newName = "changed name"
        account1.name = newName
        accountRepository.update(account1)
        accountRepository.getAll().observeOnce {
            assertEquals(it.first().name, newName)
        }
    }

    @Test
    fun getAllTest() {
        accountRepository.add(account1)
        accountRepository.add(account2)
        accountRepository.getAll().observeOnce {
            assertEquals(it, listOf(account1,
                account2))
        }
    }
}