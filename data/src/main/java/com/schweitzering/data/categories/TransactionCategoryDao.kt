package com.schweitzering.data.categories

import androidx.lifecycle.LiveData
import androidx.room.*
import com.schweitzering.domain.transaction.TransactionType

@Dao
interface TransactionCategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg category: TransactionCategoryEntity)

    @Delete
    suspend fun delete(category: TransactionCategoryEntity)

    @Query("SELECT * FROM transaction_categories WHERE type == :type")
    fun getAllByType(type: TransactionType): LiveData<List<TransactionCategoryEntity>>

    @Query("SELECT * FROM transaction_categories")
    fun getAll(): LiveData<List<TransactionCategoryEntity>>

    @Query("SELECT * FROM transaction_categories WHERE id == :id")
    fun getById(id: Int): LiveData<TransactionCategoryEntity>


}