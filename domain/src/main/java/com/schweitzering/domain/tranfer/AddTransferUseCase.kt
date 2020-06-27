package com.schweitzering.domain.tranfer

import com.schweitzering.domain.accounts.AccountRepository

class AddTransferUseCase(private val transferRepository: TransferRepository,
                         private val accountRepository: AccountRepository) {

    fun execute(transfer: Transfer) {
        transferRepository.insert(transfer)
        transfer.destinationAccount.balance += transfer.value
        transfer.originAccount.balance -= transfer.value
        accountRepository.update(transfer.originAccount)
        accountRepository.update(transfer.destinationAccount)
    }

}