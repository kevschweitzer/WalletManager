package com.schweitzering.data.inject

import androidx.room.Room
import com.schweitzering.data.categorytypes.CategoryTypesDatabaseManager
import com.schweitzering.data.categorytypes.CategoryTypesRepositoryImpl
import com.schweitzering.data.fixedExpenses.FixedExpensesDatabaseManager
import com.schweitzering.data.fixedExpenses.FixedExpensesRepositoryImpl
import com.schweitzering.data.xsupport.database.AppDatabase
import com.schweitzering.data.transaction.TransactionDatabaseManager
import com.schweitzering.data.transaction.TransactionsRepositoryImpl
import com.schweitzering.domain.categories.CategoryTypesRepository
import com.schweitzering.domain.fixedExpenses.FixedExpensesRepository
import com.schweitzering.domain.transaction.TransactionsRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java, "wallet-database"
        ).build()
    }

    factory<CategoryTypesRepository> {CategoryTypesRepositoryImpl(androidContext(), get())}

    factory {CategoryTypesDatabaseManager(get())}

    factory<TransactionsRepository> {TransactionsRepositoryImpl(get())}

    factory {TransactionDatabaseManager(get())}

    factory<FixedExpensesRepository> { FixedExpensesRepositoryImpl(get()) }

    factory { FixedExpensesDatabaseManager(get()) }
}