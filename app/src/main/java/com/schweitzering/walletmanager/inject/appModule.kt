package com.schweitzering.walletmanager.inject

import com.schweitzering.domain.balance.GetPartialBalanceUseCase
import com.schweitzering.domain.balance.GetTotalBalanceUseCase
import com.schweitzering.domain.categories.GetCategoryTypesUseCase
import com.schweitzering.domain.fixedExpenses.*
import com.schweitzering.domain.fixedExpenses.generator.GetAllFixedExpensesGeneratorsUseCase
import com.schweitzering.domain.fixedExpenses.generator.NewFixedExpenseGeneratorUseCase
import com.schweitzering.domain.transaction.AddTransactionUseCase
import com.schweitzering.domain.transaction.GetAllTransactionsUseCase
import com.schweitzering.walletmanager.main.MainActivity
import com.schweitzering.walletmanager.balance.BalanceViewModel
import com.schweitzering.walletmanager.fixedExpenses.FixedExpensesViewModel
import com.schweitzering.walletmanager.fixedExpenses.generator.NewFixedExpenseGeneratorActivity
import com.schweitzering.walletmanager.fixedExpenses.generator.NewFixedExpenseGeneratorViewModel
import com.schweitzering.walletmanager.fixedExpenses.generator.list.FixedExpensesGeneratorsActivity
import com.schweitzering.walletmanager.fixedExpenses.generator.list.FixedExpensesGeneratorsViewModel
import com.schweitzering.walletmanager.fixedExpenses.worker.FixedExpensesWorkerViewModel
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
        factory { FixedExpensesViewModel(get(), get())}
    }

    scope(named<NewFixedExpenseGeneratorActivity>()) {
        scoped {NewFixedExpenseGeneratorViewModel(get())}
    }

    scope(named<FixedExpensesGeneratorsActivity>()) {
        scoped {FixedExpensesGeneratorsViewModel(get())}
    }

    factory {GetPartialBalanceUseCase(get())}

    factory {GetTotalBalanceUseCase(get())}

    factory {GetCategoryTypesUseCase(get())}

    factory {AddTransactionUseCase(get())}

    factory {GetAllTransactionsUseCase(get())}

    factory {NewFixedExpenseUseCase(get())}

    factory { GetFixedExpensesUseCase(get()) }

    factory { GetFixedExpensesByPaymentUseCase(get()) }

    factory { PayFixedExpenseUseCase(get(), get()) }

    factory { NewFixedExpenseGeneratorUseCase(get()) }

    factory { GetAllFixedExpensesGeneratorsUseCase(get()) }

    factory { FixedExpensesWorkerViewModel(get()) }

    factory {CreateFixedExpensesForPeriodUseCase(get(), get())}
}