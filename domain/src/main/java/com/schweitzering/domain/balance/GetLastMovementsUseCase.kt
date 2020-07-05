package com.schweitzering.domain.balance

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.schweitzering.domain.movements.Movement
import com.schweitzering.domain.tranfer.TransferRepository
import com.schweitzering.domain.transaction.TransactionsRepository
import kotlinx.coroutines.flow.combine

class GetLastMovementsUseCase(private val transactionsRepository: TransactionsRepository,
                              private val transferRepository: TransferRepository) {

    fun execute(): LiveData<List<Movement>> {
        var data1 = listOf<Movement>()
        var data2 = listOf<Movement>()
        val mediator = MediatorLiveData<List<Movement>>()
        mediator.addSource(transactionsRepository.getAll()){
            data1 = it
            val result = mutableListOf<Movement>().apply {
                addAll(data1)
                addAll(data2)
            }
            mediator.postValue(result)
        }
        mediator.addSource(transferRepository.getAll()){
            data2 = it
            val result = mutableListOf<Movement>().apply {
                addAll(data1)
                addAll(data2)
            }
            mediator.postValue(result)
        }
        return mediator
    }

}