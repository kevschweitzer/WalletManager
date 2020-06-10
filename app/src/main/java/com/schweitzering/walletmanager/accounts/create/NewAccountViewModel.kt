package com.schweitzering.walletmanager.accounts.create

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class NewAccountViewModel {

    sealed class State {
        object FinishedState: State()
    }

    private var _state = MutableLiveData<State>()
    var state: LiveData<State> = _state
    var name: String = ""
    var description: String = ""

    fun onCreateAccountClicked() {
        Log.e("name", name)
        Log.e("description", description)
        _state.value = State.FinishedState
    }

}