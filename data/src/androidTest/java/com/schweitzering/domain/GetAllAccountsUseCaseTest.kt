package com.schweitzering.domain

import androidx.lifecycle.MutableLiveData
import com.schweitzering.account1
import com.schweitzering.account2
import com.schweitzering.data.accounts.AccountRepositoryImpl
import com.schweitzering.data.observeOnce
import com.schweitzering.data.transaction.TransactionsRepositoryImpl
import com.schweitzering.domain.accounts.AccountRepository
import com.schweitzering.domain.accounts.GetAllAccountsUseCase
import com.schweitzering.domain.transaction.TransactionsRepository
import com.schweitzering.transaction1
import com.schweitzering.transaction2
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.doReturn

@RunWith(JUnit4::class)
class GetAllAccountsUseCaseTest {


    private lateinit var accountsRepositoryMock: AccountRepository
    private lateinit var transactionRepositoryMock: TransactionsRepository

    @Before
    fun setup() {
        accountsRepositoryMock = mock<AccountRepositoryImpl> {
            on { getAll() } doReturn MutableLiveData(listOf(account1, account2))
        }
        transactionRepositoryMock = mock<TransactionsRepositoryImpl> {
            on { getByAccount(account1.id) } doReturn MutableLiveData(listOf(transaction1))
            on { getByAccount(account2.id) } doReturn MutableLiveData(listOf(transaction2))
        }
    }

    @Test
    fun testUseCase() {
        account1.transactions.add(
            transaction1)
        account2.transactions.add(
            transaction2)
        val getAllAccountsUseCase = GetAllAccountsUseCase(accountsRepositoryMock, transactionRepositoryMock)
        getAllAccountsUseCase.execute().observeOnce {
            assertEquals(it, listOf(account1,
                account2))
        }
    }
}