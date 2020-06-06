package com.schweitzering.walletmanager.accounts

import com.schweitzering.domain.accounts.GetAllAccountsUseCase

class AccountsViewModel(private val getAllAccountsUseCase: GetAllAccountsUseCase) {

    val accounts = getAllAccountsUseCase.execute()
}