package com.schweitzering.data.transaction

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TransactionsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: TransactionEntity)

    @Transaction
    @Query("SELECT * FROM transactions")
    fun getAll(): LiveData<List<TransactionWithCategoryRelation>>

    @Delete
    suspend fun delete(entity: TransactionEntity)

    @Query("SELECT * FROM transactions WHERE categoryId == :categoryId")
    fun getByCategory(categoryId: Int): LiveData<List<TransactionEntity>>

    @Query("SELECT * FROM transactions WHERE type == :type")
    fun getByType(type: String): LiveData<List<TransactionEntity>>

    @Transaction
    @Query("SELECT * FROM transactions WHERE date BETWEEN :initialDate AND :finalDate")
    fun getBetween(initialDate: Long?, finalDate: Long?): LiveData<List<TransactionWithCategoryRelation>>

    @Transaction
    @Query("SELECT * FROM transactions WHERE accountId == :accountId")
    fun getByAccount(accountId: Int): LiveData<List<TransactionWithCategoryRelation>>
}
