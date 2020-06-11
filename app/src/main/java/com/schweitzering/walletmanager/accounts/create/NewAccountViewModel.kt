package com.schweitzering.walletmanager.accounts.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.schweitzering.domain.accounts.Account
import com.schweitzering.domain.accounts.NewAccountUseCase
import com.schweitzering.walletmanager.xsupport.utils.Constants.Companion.EMPTY_STRING

class NewAccountViewModel(private val newAccountUseCase: NewAccountUseCase) {

    sealed class State {
        object FinishedState: State()
    }

    private var _state = MutableLiveData<State>()
    var state: LiveData<State> = _state
    var name: String = EMPTY_STRING
    var description: String = EMPTY_STRING

    fun onCreateAccountClicked() {
        newAccountUseCase.execute(Account(name = name, description = description))
        _state.value = State.FinishedState
    }
}