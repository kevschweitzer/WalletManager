package com.schweitzering.data.debts

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DebtsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(debt: DebtEntity)

    @Delete
    suspend fun delete(debt: DebtEntity)

    @Update
    suspend fun update(debt: DebtEntity)

    @Transaction
    @Query("SELECT * FROM debts")
    fun getAll(): LiveData<List<DebtWithCategoryRelation>>
}