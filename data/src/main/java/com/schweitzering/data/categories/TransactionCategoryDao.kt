package com.schweitzering.data.categories

import androidx.lifecycle.LiveData
import androidx.room.*
import com.schweitzering.domain.categories.TransactionCategory
import com.schweitzering.domain.transaction.TransactionType

@Dao
interface TransactionCategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(category: TransactionCategoryEntity)

    @Delete
    suspend fun delete(category: TransactionCategoryEntity)

    @Query("SELECT * FROM transaction_categories WHERE type == :type")
    fun getAllByType(type: TransactionType): LiveData<List<TransactionCategoryEntity>>

    @Query("SELECT * FROM transaction_categories")
    fun getAll(): LiveData<List<TransactionCategoryEntity>>


}