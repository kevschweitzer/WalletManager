package com.schweitzering.data.transaction.income

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import java.sql.Timestamp


@Dao
interface IncomeDao {

    @Query("SELECT * FROM incomes")
    fun getAll(): List<IncomeEntity>

    @Query("SELECT * FROM incomes WHERE date >= :begin AND date <= :end")
    fun getBetween(begin: Timestamp, end: Timestamp): List<IncomeEntity>

    @Insert
    fun insert(income: IncomeEntity)

    @Delete
    fun delete(income: IncomeEntity)
}