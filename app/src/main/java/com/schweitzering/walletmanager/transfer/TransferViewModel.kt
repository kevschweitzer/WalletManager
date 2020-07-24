package com.schweitzering.walletmanager.transfer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.schweitzering.domain.accounts.GetAllAccountsUseCase
import com.schweitzering.domain.tranfer.AddTransferUseCase
import com.schweitzering.domain.tranfer.Transfer
import java.sql.Timestamp

class TransferViewModel(private val getAllAccountsUseCase: GetAllAccountsUseCase,
                        private val addTransferUseCase: AddTransferUseCase): ViewModel(){

    sealed class TransferState{
        object ContinueClicked: TransferState()
    }

    var value: Float = 10f
    var description: String = ""
    val accounts = getAllAccountsUseCase.execute()
    var selectedOriginPosition = 0
    var selectedDestinationPosition = 0
    private val _state = MutableLiveData<TransferState>()
    val state: LiveData<TransferState>
        get() = _state


    fun onContinueClicked() {
        if(validAccounts()) {
            _state.value = TransferState.ContinueClicked
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

    fun newTransfer() = addTransferUseCase.execute(getCurrentTransfer())

}