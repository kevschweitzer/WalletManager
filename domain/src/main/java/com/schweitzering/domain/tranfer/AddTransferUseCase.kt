package com.schweitzering.domain.tranfer

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.schweitzering.domain.ActionResponse
import com.schweitzering.domain.accounts.AccountRepository

class AddTransferUseCase(private val transferRepository: TransferRepository,
                         private val accountRepository: AccountRepository) {

    fun execute(transfer: Transfer): LiveData<ActionResponse> {
        return liveData {
            if(transfer.value > transfer.originAccount.balance) {
                emit(ActionResponse.NotEnoughMoney)
            } else {
                transferRepository.insert(transfer)
                transfer.destinationAccount.balance += transfer.value
                transfer.originAccount.balance -= transfer.value
                accountRepository.update(transfer.originAccount)
                accountRepository.update(transfer.destinationAccount)
                emit(ActionResponse.Correct)
            }
        }

    }

}