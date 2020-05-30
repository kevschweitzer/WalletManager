package com.schweitzering.data.transaction

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TransactionsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: TransactionEntity)

    @Query("SELECT * FROM transactions")
    fun getAll(): LiveData<List<TransactionEntity>>

    @Delete
    suspend fun delete(entity: TransactionEntity)

    @Query("SELECT * FROM transactions WHERE category == :category")
    fun getByCategory(category: String): LiveData<List<TransactionEntity>>

    @Query("SELECT * FROM transactions WHERE type == :type")
    fun getByType(type: String): LiveData<List<TransactionEntity>>

    @Query("SELECT * FROM transactions WHERE date BETWEEN :initialDate AND :finalDate")
    fun getBetween(initialDate: Long?, finalDate: Long?): LiveData<List<TransactionEntity>>
}
