package com.schweitzering.walletmanager.transfer

import androidx.lifecycle.ViewModel
import com.schweitzering.domain.accounts.GetAllAccountsUseCase
import com.schweitzering.domain.tranfer.AddTransferUseCase
import com.schweitzering.domain.tranfer.Transfer
import java.sql.Timestamp

class TransferViewModel(private val getAllAccountsUseCase: GetAllAccountsUseCase,
                        private val addTransferUseCase: AddTransferUseCase): ViewModel(){

    var value: Float = 10f
    var description: String = ""
    val accounts = getAllAccountsUseCase.execute()
    var selectedOriginPosition = 0
    var selectedDestinationPosition = 0

    fun onContinueClicked() {
        if(validAccounts()) {
            addTransferUseCase.execute(getCurrentTransfer())
        }
    }

    private fun getCurrentTransfer() = Transfer(
        value = value,
        description = description,
        date = Timestamp(System.currentTimeMillis()),
        originAccount = accounts.value!!.get(selectedOriginPosition),
        destinationAccount = accounts.value!!.get(selectedDestinationPosition)
    )

    private fun validAccounts(): Boolean {
        val origin = accounts.value?.get(selectedOriginPosition)
        val destination = accounts.value?.get(selectedDestinationPosition)
        return origin != null && destination != null
    }
}