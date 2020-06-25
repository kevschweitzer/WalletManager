package com.schweitzering.domain.tranfer

import androidx.lifecycle.LiveData

interface TransferRepository {
    fun insert(transfer: Transfer)
    fun delete(transfer: Transfer)
    fun update(transfer: Transfer)
    fun getAll(): LiveData<List<Transfer>>
}