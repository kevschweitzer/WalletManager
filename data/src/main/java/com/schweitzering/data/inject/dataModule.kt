package com.schweitzering.data.inject

import androidx.room.Room
import androidx.room.RoomDatabase
import com.schweitzering.data.categorytypes.CategoryTypesDatabaseManager
import com.schweitzering.data.categorytypes.CategoryTypesRepositoryImpl
import com.schweitzering.data.database.AppDatabase
import com.schweitzering.data.transaction.TransactionDatabaseManager
import com.schweitzering.data.transaction.TransactionsRepositoryImpl
import com.schweitzering.domain.categories.CategoryTypesRepository
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

    single<CategoryTypesRepository> {CategoryTypesRepositoryImpl(androidContext(), get())}

    single {CategoryTypesDatabaseManager(get())}

    single<TransactionsRepository> {TransactionsRepositoryImpl(get())}

    single {TransactionDatabaseManager(get())}
}