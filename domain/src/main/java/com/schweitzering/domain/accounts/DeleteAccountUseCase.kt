package com.schweitzering.domain.accounts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.schweitzering.domain.ActionResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DeleteAccountUseCase(private val repository: AccountRepository) {

    fun execute(account: Account): LiveData<ActionResponse> {
        return if(account.balance == 0f){
            repository.remove(account)
        } else {
            val response = MutableLiveData<ActionResponse>()
            GlobalScope.launch {
                response.postValue(ActionResponse.NotEmptyAccountError)
            }
            response
        }
    }
}