package com.schweitzering.domain.fixedExpenses

class GetFixedExpensesByPaymentUseCase(private val repository: FixedExpensesRepository) {

    fun execute(isPaid: Boolean) = repository.getByPayment(isPaid)
}