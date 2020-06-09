package com.schweitzering.data.accounts

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AccountsDao {

    @Insert
    suspend fun insert(entity: AccountEntity)

    @Delete
    suspend fun remove(entity: AccountEntity)

    @Update
    suspend fun update(entity: AccountEntity)

    @Query("SELECT * FROM accounts")
    fun getAll(): LiveData<List<AccountEntity>>

    @Transaction
    @Query("SELECT * FROM accounts")
    fun getAccountsWithTransactions(): LiveData<List<AccountWithTransactionsRelation>>
}