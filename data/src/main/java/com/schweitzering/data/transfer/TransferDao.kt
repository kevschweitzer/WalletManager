package com.schweitzering.data.transfer

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TransferDao {
    @Insert
    suspend fun insert(transfer: TransferEntity)

    @Delete
    suspend fun delete(transfer: TransferEntity)

    @Update
    suspend fun update(transfer: TransferEntity)

    @Query("SELECT * FROM transfers")
    fun getAll(): LiveData<List<TransferWithAccounts>>
}