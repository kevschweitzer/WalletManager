package com.schweitzering.data.fixedExpenses.generator

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Single

@Dao
interface FixedExpensesGeneratorsDao {

    @Insert
    suspend fun insert(generator: FixedExpenseGeneratorEntity)

    @Delete
    suspend fun delete(generator: FixedExpenseGeneratorEntity)

    @Query("SELECT * FROM fixed_expenses_generators")
    fun getAll(): Single<List<FixedExpenseGeneratorEntity>>
}