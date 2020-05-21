package com.schweitzering.walletmanager.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingsViewModel: ViewModel(){

    sealed class State {
        object TransactionCategoriesClicked: State()
    }

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    fun onTransactionCategoriesClicked() {
        _state.value = State.TransactionCategoriesClicked
    }
}