package com.schweitzering.domain.balance

import androidx.lifecycle.LiveData

interface BalanceRepository {

    fun getLastTotalBalance(): LiveData<Float>

    fun getLastPartialBalance(): LiveData<Float>
}