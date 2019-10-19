package com.schweitzering.data.transaction.expense

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import java.sql.Timestamp

@Dao
interface ExpenseDao {

    @Query("SELECT * FROM expenses")
    fun getAll(): List<ExpenseEntity>

    @Query("SELECT * FROM expenses WHERE date >= :begin AND date <= :end")
    fun getBetween(begin: Timestamp, end: Timestamp): List<ExpenseEntity>

    @Insert
    fun insert(expense: ExpenseEntity)

    @Delete
    fun delete(expense: ExpenseEntity)
}