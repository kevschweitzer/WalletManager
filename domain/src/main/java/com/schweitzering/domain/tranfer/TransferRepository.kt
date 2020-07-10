package com.schweitzering.domain.tranfer

import androidx.lifecycle.LiveData
import com.schweitzering.domain.BaseRepository

interface TransferRepository: BaseRepository<Transfer> {
    override fun insert(model: Transfer)
    override fun delete(model: Transfer)
    override fun update(model: Transfer)
    override fun getAll(): LiveData<List<Transfer>>
}