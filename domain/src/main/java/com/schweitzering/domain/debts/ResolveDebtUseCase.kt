package com.schweitzering.domain.debts

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.schweitzering.domain.ActionResponse
import com.schweitzering.domain.accounts.Account
import com.schweitzering.domain.transaction.AddTransactionUseCase
import com.schweitzering.domain.transaction.Transaction
import java.sql.Timestamp

class ResolveDebtUseCase(private val debtsRepository: DebtsRepository,
                        private val addTransactionUseCase: AddTransactionUseCase) {

    fun execute(debt: Debt, account: Account): LiveData<ActionResponse> {
        debt.apply {
            isResolved = true
            resolveDate = Timestamp(System.currentTimeMillis())
        }
        return Transformations.map(addTransactionUseCase.execute(
            Transaction(
                value = debt.value,
                date = debt.resolveDate,
                description = debt.description,
                type = debt.type,
                category = debt.category,
                account = account
            ),
            account
        )) {
            if(it is ActionResponse.Correct) {
                debtsRepository.update(debt)
            }
            it
        }
    }
}