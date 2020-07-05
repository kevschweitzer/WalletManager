package com.schweitzering.walletmanager.accounts.create

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.schweitzering.domain.accounts.Account
import com.schweitzering.domain.accounts.NewAccountUseCase
import com.schweitzering.domain.accounts.UpdateAccountUseCase
import com.schweitzering.walletmanager.accounts.create.NewAccountActivity.Companion.ACCOUNT_KEY
import com.schweitzering.walletmanager.xsupport.utils.Constants.Companion.EMPTY_STRING

class NewAccountViewModel(private val newAccountUseCase: NewAccountUseCase,
                          private val updateAccountUseCase: UpdateAccountUseCase) {

    sealed class State {
        object FinishedState: State()
    }

    private var editableAccount: Account? = null
    private var _state = MutableLiveData<State>()
    var state: LiveData<State> = _state
    var name: String = EMPTY_STRING
    var description: String = EMPTY_STRING

    fun onCreateAccountClicked() {
        editableAccount?.let {
            it.apply {
                name = this@NewAccountViewModel.name
                description = this@NewAccountViewModel.description
                balance = it.balance
            }
            updateAccountUseCase.execute(it)
        } ?: newAccountUseCase.execute(Account(name = name, description = description))
        _state.value = State.FinishedState
    }

    fun handleIntent(intent: Intent) {
        editableAccount = intent.getSerializableExtra(ACCOUNT_KEY) as? Account
        editableAccount?.let {
            name = it.name
            description = it.description
        }
    }
}