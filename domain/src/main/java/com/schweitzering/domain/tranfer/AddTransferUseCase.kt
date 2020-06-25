package com.schweitzering.domain.tranfer

class AddTransferUseCase(private val repository: TransferRepository) {

    fun execute(transfer: Transfer) {
        repository.insert(transfer)
    }
}
