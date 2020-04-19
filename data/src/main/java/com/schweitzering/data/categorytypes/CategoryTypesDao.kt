package com.schweitzering.data.categorytypes

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.schweitzering.domain.transaction.TransactionCategory

@Dao
interface CategoryTypesDao {

    @Insert
    suspend fun insert(type: CategoryTypeEntity)

    @Delete
    suspend fun delete(type: CategoryTypeEntity)

    @Query("SELECT type FROM category_types WHERE category == :category")
    fun getAllByCategory(category: TransactionCategory): LiveData<List<String>>

}