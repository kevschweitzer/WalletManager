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

    @Transaction
    @Query("SELECT * FROM accounts")
    fun getAll(): LiveData<List<AccountWithTransactionsRelation>>

    @Transaction
    @Query("SELECT * FROM accounts")
    fun getAccountsWithTransactions(): LiveData<List<AccountWithTransactionsRelation>>

    @Transaction
    @Query("SELECT * FROM accounts WHERE account_id = :id")
    fun getById(id: Int): LiveData<AccountWithTransactionsRelation>
}