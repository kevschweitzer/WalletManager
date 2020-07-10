package com.schweitzering.domain.debts

import com.schweitzering.domain.transaction.TransactionsRepository
import java.sql.Timestamp

class ResolveDebtUseCase(private val debtsRepository: DebtsRepository,
                        private val transactionsRepository: TransactionsRepository) {

    fun execute(debt: Debt) {
        debt.apply {
            isResolved = true
            resolveDate = Timestamp(System.currentTimeMillis())
        }
        debt.transaction.date = debt.resolveDate
        transactionsRepository.insert(debt.transaction)
        debtsRepository.update(debt)
    }
}