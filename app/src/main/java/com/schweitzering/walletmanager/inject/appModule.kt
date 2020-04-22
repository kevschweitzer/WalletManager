package com.schweitzering.walletmanager.inject

import com.schweitzering.domain.balance.GetPartialBalanceUseCase
import com.schweitzering.domain.balance.GetTotalBalanceUseCase
import com.schweitzering.domain.categories.GetCategoryTypesUseCase
import com.schweitzering.domain.transaction.AddTransactionUseCase
import com.schweitzering.domain.transaction.GetAllTransactionsUseCase
import com.schweitzering.walletmanager.main.MainActivity
import com.schweitzering.walletmanager.balance.BalanceViewModel
import com.schweitzering.walletmanager.fixedExpenses.FixedExpensesViewModel
import com.schweitzering.walletmanager.transaction.TransactionActivity
import com.schweitzering.walletmanager.transaction.TransactionViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module{

    scope(named<TransactionActivity>()) {
        scoped { TransactionViewModel(get(), get()) }
    }

    scope(named<MainActivity>()) {
        factory { BalanceViewModel(get(), get(), get()) }
        factory { FixedExpensesViewModel()}
    }

    single {GetPartialBalanceUseCase(get())}

    single {GetTotalBalanceUseCase(get())}

    single {GetCategoryTypesUseCase(get())}

    single {AddTransactionUseCase(get())}

    single {GetAllTransactionsUseCase(get())}
}