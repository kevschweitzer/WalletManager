package com.schweitzering.domain.debts

import com.schweitzering.domain.transaction.Transaction
import com.schweitzering.domain.transaction.TransactionsRepository
import java.sql.Timestamp

class ResolveDebtUseCase(private val debtsRepository: DebtsRepository,
                        private val transactionsRepository: TransactionsRepository) {

    fun execute(debt: Debt) {
        debt.apply {
            isResolved = true
            resolveDate = Timestamp(System.currentTimeMillis())
        }
        transactionsRepository.add(Transaction(
            value = debt.value,
            date = Timestamp(System.currentTimeMillis()),
            category = debt.category,
            categoryType = debt.categoryType)
        )
        debtsRepository.update(debt)
    }
}