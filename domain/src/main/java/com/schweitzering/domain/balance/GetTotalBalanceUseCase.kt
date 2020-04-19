package com.schweitzering.domain.balance

import com.schweitzering.domain.transaction.TransactionsRepository


/*
    Total balance represents all the transactions balance, including saving and investments
 */
class GetTotalBalanceUseCase(private val balanceRepository: BalanceRepository,
                             private val transactionsRepository: TransactionsRepository) {

    fun execute() {

    }
}