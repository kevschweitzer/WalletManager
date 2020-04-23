package com.schweitzering.data.fixedExpenses

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FixedExpensesDao {

    @Insert
    suspend fun insert(expense: FixedExpenseEntity)

    @Delete
    suspend fun delete(expense: FixedExpenseEntity)

    @Query("SELECT * FROM fixed_expenses")
    fun getAll(): LiveData<List<FixedExpenseEntity>>

    @Update
    suspend fun update(expense: FixedExpenseEntity)

    @Query("SELECT * FROM fixed_expenses WHERE isAlreadyPaid = :isPaid")
    fun getByPayment(isPaid: Boolean): LiveData<List<FixedExpenseEntity>>
}