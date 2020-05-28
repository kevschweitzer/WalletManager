package com.schweitzering.walletmanager.inject

import com.schweitzering.domain.balance.GetPartialBalanceUseCase
import com.schweitzering.domain.balance.GetTotalBalanceUseCase
import com.schweitzering.domain.categories.GetAllCategoriesTypesUseCase
import com.schweitzering.domain.categories.GetCategoryTypesUseCase
import com.schweitzering.domain.debts.GetAllDebtsUseCase
import com.schweitzering.domain.debts.NewDebtUseCase
import com.schweitzering.domain.debts.ResolveDebtUseCase
import com.schweitzering.domain.fixedExpenses.CreateFixedExpensesForPeriodUseCase
import com.schweitzering.domain.fixedExpenses.GetFixedExpensesUseCase
import com.schweitzering.domain.fixedExpenses.NewFixedExpenseUseCase
import com.schweitzering.domain.fixedExpenses.PayFixedExpenseUseCase
import com.schweitzering.domain.fixedExpenses.generator.GetAllFixedExpensesGeneratorsUseCase
import com.schweitzering.domain.fixedExpenses.generator.NewFixedExpenseGeneratorUseCase
import com.schweitzering.domain.transaction.AddTransactionUseCase
import com.schweitzering.domain.transaction.GetAllTransactionsUseCase
import com.schweitzering.walletmanager.balance.BalanceViewModel
import com.schweitzering.walletmanager.debts.DebtsViewModel
import com.schweitzering.walletmanager.debts.create.NewDebtActivity
import com.schweitzering.walletmanager.debts.create.NewDebtViewModel
import com.schweitzering.walletmanager.fixedExpenses.generator.create.NewFixedExpenseGeneratorActivity
import com.schweitzering.walletmanager.fixedExpenses.generator.create.NewFixedExpenseGeneratorViewModel
import com.schweitzering.walletmanager.fixedExpenses.generator.list.FixedExpensesGeneratorsActivity
import com.schweitzering.walletmanager.fixedExpenses.generator.list.FixedExpensesGeneratorsViewModel
import com.schweitzering.walletmanager.fixedExpenses.list.FixedExpensesViewModel
import com.schweitzering.walletmanager.fixedExpenses.worker.FixedExpensesWorkerViewModel
import com.schweitzering.walletmanager.main.MainActivity
import com.schweitzering.walletmanager.settings.SettingsActivity
import com.schweitzering.walletmanager.settings.SettingsViewModel
import com.schweitzering.walletmanager.settings.categories.create.NewCategoryActivity
import com.schweitzering.walletmanager.settings.categories.create.NewCategoryViewModel
import com.schweitzering.walletmanager.settings.categories.list.CategoriesSettingsActivity
import com.schweitzering.walletmanager.settings.categories.list.CategoriesSettingsViewModel
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
        factory { FixedExpensesViewModel(get(), get()) }
        factory { DebtsViewModel(get(), get())}
    }

    scope(named<NewFixedExpenseGeneratorActivity>()) {
        scoped {
            NewFixedExpenseGeneratorViewModel(
                get()
            )
        }
    }

    scope(named<SettingsActivity>()) {
        scoped { SettingsViewModel() }
    }

    scope(named<FixedExpensesGeneratorsActivity>()) {
        scoped {FixedExpensesGeneratorsViewModel(get())}
    }

    scope(named<NewDebtActivity>()) {
        scoped {NewDebtViewModel(get())}
    }

    scope(named<CategoriesSettingsActivity>()) {
        scoped {
            CategoriesSettingsViewModel(
                get()
            )
        }
    }

    scope(named<NewCategoryActivity>()) {
        scoped { NewCategoryViewModel() }
    }

    factory {GetPartialBalanceUseCase(get())}

    factory {GetTotalBalanceUseCase(get())}

    factory {GetCategoryTypesUseCase(get())}

    factory {AddTransactionUseCase(get())}

    factory {GetAllTransactionsUseCase(get())}

    factory {NewFixedExpenseUseCase(get())}

    factory { GetFixedExpensesUseCase(get()) }

    factory { PayFixedExpenseUseCase(get(), get()) }

    factory { NewFixedExpenseGeneratorUseCase(get()) }

    factory { GetAllFixedExpensesGeneratorsUseCase(get()) }

    factory { FixedExpensesWorkerViewModel(get()) }

    factory {CreateFixedExpensesForPeriodUseCase(get(), get())}

    factory { GetAllDebtsUseCase(get()) }

    factory { NewDebtUseCase(get()) }

    factory { ResolveDebtUseCase(get(), get()) }

    factory { GetAllCategoriesTypesUseCase(get()) }
}