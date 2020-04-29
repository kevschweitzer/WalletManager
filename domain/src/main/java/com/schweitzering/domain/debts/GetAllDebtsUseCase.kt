package com.schweitzering.domain.debts

class GetAllDebtsUseCase(private val debtsRepository: DebtsRepository) {

    fun execute() = debtsRepository.getAll()
}