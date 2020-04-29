package com.schweitzering.domain.debts

class NewDebtUseCase(private val debtsRepository: DebtsRepository) {

    fun execute(debt: Debt) {
        debtsRepository.insert(debt)
    }
}