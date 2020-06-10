package com.schweitzering.walletmanager.accounts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.schweitzering.domain.accounts.GetAllAccountsUseCase

class AccountsViewModel(private val getAllAccountsUseCase: GetAllAccountsUseCase) {

    sealed class State {
        object NewAccountState: State()
    }

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    val accounts = getAllAccountsUseCase.execute()

    fun onNewAccountClicked() {
        _state.value = State.NewAccountState
    }
}