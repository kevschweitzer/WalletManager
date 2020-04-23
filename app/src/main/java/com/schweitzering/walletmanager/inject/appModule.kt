package com.schweitzering.walletmanager.inject

import com.schweitzering.data.fixedExpenses.GetFixedExpensesUseCase
import com.schweitzering.domain.balance.GetPartialBalanceUseCase
import com.schweitzering.domain.balance.GetTotalBalanceUseCase
import com.schweitzering.domain.categories.GetCategoryTypesUseCase
import com.schweitzering.domain.fixedExpenses.NewFixedExpenseUseCase
import com.schweitzering.domain.transaction.AddTransactionUseCase
import com.schweitzering.domain.transaction.GetAllTransactionsUseCase
import com.schweitzering.walletmanager.main.MainActivity
import com.schweitzering.walletmanager.balance.BalanceViewModel
import com.schweitzering.walletmanager.fixedExpenses.FixedExpensesViewModel
import com.schweitzering.walletmanager.fixedExpenses.create.NewFixedExpenseActivity
import com.schweitzering.walletmanager.fixedExpenses.create.NewFixedExpenseViewModel
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
        factory { FixedExpensesViewModel(get())}
    }

    scope(named<NewFixedExpenseActivity>()) {
        scoped {NewFixedExpenseViewModel(get())}
    }

    factory {GetPartialBalanceUseCase(get())}

    factory {GetTotalBalanceUseCase(get())}

    factory {GetCategoryTypesUseCase(get())}

    factory {AddTransactionUseCase(get())}

    factory {GetAllTransactionsUseCase(get())}

    factory {NewFixedExpenseUseCase(get())}

    factory {GetFixedExpensesUseCase(get())}
}