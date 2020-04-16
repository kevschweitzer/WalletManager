package com.schweitzering.data.finance

import androidx.lifecycle.LiveData
import androidx.room.*
import com.schweitzering.domain.finance.FinanceCategory
import java.sql.Timestamp

@Dao
interface FinanceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: FinanceEntity)

    @Query("SELECT * FROM finances")
    fun getAll(): LiveData<List<FinanceEntity>>

    @Delete
    suspend fun delete(entity: FinanceEntity)

    @Query("SELECT * FROM finances WHERE category == :category")
    fun getByCategory(category: String): LiveData<List<FinanceEntity>>

    @Query("SELECT * FROM finances WHERE financeCategoryType == :type")
    fun getByFinanceCategoryType(type: String): LiveData<List<FinanceEntity>>

    @Query("SELECT * FROM finances WHERE date BETWEEN :initialDate AND :finalDate")
    fun getBetween(initialDate: Long?, finalDate: Long?): LiveData<List<FinanceEntity>>
}
