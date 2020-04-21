package com.schweitzering.data.fixedExpenses

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FixedExpensesDao {

    @Insert
    suspend fun insert(expense: FixedExpenseEntity)

    @Delete
    suspend fun delete(exepense: FixedExpenseEntity)

    @Query("SELECT * FROM fixed_expenses")
    fun getAll(): LiveData<List<FixedExpenseEntity>>
}