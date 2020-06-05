package com.schweitzering.data.inject

import com.schweitzering.data.accounts.AccountRepositoryImpl
import com.schweitzering.data.categories.TransactionCategoryDatabaseManager
import com.schweitzering.data.categories.TransactionCategoryRepositoryImpl
import com.schweitzering.data.debts.DebtsDatabaseManager
import com.schweitzering.data.debts.DebtsRepositoryImpl
import com.schweitzering.data.fixedExpenses.FixedExpensesDatabaseManager
import com.schweitzering.data.fixedExpenses.FixedExpensesRepositoryImpl
import com.schweitzering.data.fixedExpenses.generator.FixedExpensesGeneratorDatabaseManager
import com.schweitzering.data.fixedExpenses.generator.FixedExpensesGeneratorRepositoryImpl
import com.schweitzering.data.transaction.TransactionDatabaseManager
import com.schweitzering.data.transaction.TransactionsRepositoryImpl
import com.schweitzering.data.xsupport.database.AppDatabase
import com.schweitzering.domain.accounts.AccountRepository
import com.schweitzering.domain.categories.TransactionCategoryRepository
import com.schweitzering.domain.debts.DebtsRepository
import com.schweitzering.domain.fixedExpenses.FixedExpensesRepository
import com.schweitzering.domain.fixedExpenses.generator.FixedExpensesGeneratorRepository
import com.schweitzering.domain.transaction.TransactionsRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    single { AppDatabase.getInstance(androidContext()) }

    factory<TransactionCategoryRepository> {TransactionCategoryRepositoryImpl(get())}

    factory {TransactionCategoryDatabaseManager(get())}

    factory<TransactionsRepository> {TransactionsRepositoryImpl(get())}

    factory {TransactionDatabaseManager(get())}

    factory<FixedExpensesRepository> { FixedExpensesRepositoryImpl(get()) }

    factory { FixedExpensesDatabaseManager(get()) }

    factory<FixedExpensesGeneratorRepository> { FixedExpensesGeneratorRepositoryImpl(get()) }

    factory { FixedExpensesGeneratorDatabaseManager(get()) }

    factory<DebtsRepository> { DebtsRepositoryImpl(get()) }

    factory { DebtsDatabaseManager(get()) }

    factory<AccountRepository> { AccountRepositoryImpl(get()) }
}