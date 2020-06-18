package com.schweitzering.domain.accounts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.schweitzering.domain.ActionResponse

class DeleteAccountUseCase(private val repository: AccountRepository) {

    fun execute(account: Account): LiveData<ActionResponse> {
        return Transformations.switchMap(repository.getAll()) {
            if (it.size > 1 && account.balance == 0f) {
                repository.remove(account)
            } else {
                val response = MutableLiveData<ActionResponse>()
                response.value = ActionResponse.CannotDeleteError
                response
            }
        }
    }
}