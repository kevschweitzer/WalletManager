package com.schweitzering.walletmanager.accounts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.schweitzering.domain.accounts.Account
import com.schweitzering.domain.accounts.DeleteAccountUseCase
import com.schweitzering.domain.accounts.GetAllAccountsUseCase

class AccountsViewModel(getAllAccountsUseCase: GetAllAccountsUseCase,
                        private val deleteAccountUseCase: DeleteAccountUseCase) {

    sealed class State {
        object NewAccountState: State()
        class DeleteAccountState(var account: Account): State()
        class EditAccountState(var account: Account): State()
    }

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    val accounts = getAllAccountsUseCase.execute()

    fun onNewAccountClicked() {
        _state.value = State.NewAccountState
    }

    fun onEditClicked(account: Account) {
        _state.value = State.EditAccountState(account)
    }

    fun onDeleteClicked(account: Account) {
        _state.value = State.DeleteAccountState(account)
    }

    fun deleteAccount(account: Account)  = deleteAccountUseCase.execute(account)

}