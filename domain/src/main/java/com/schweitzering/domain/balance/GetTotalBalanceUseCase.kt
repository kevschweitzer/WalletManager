package com.schweitzering.domain.balance

import com.schweitzering.domain.transaction.TransactionsRepository

class GetTotalBalanceUseCase(private val balanceRepository: BalanceRepository,
                             private val transactionsRepository: TransactionsRepository) {

    fun execute() {

    }
}