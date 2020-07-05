package com.schweitzering.domain.accounts

import androidx.lifecycle.*
import com.schweitzering.domain.ActionResponse

class DeleteAccountUseCase(private val repository: AccountRepository) {

    fun execute(account: Account): LiveData<ActionResponse> {
        return if(account.balance == 0f) {
            repository.remove(account)
        } else {
            liveData<ActionResponse> { emit(ActionResponse.CannotDeleteError) }
        }
    }
}