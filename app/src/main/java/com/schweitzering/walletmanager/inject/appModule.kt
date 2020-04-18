package com.schweitzering.walletmanager.inject

import com.schweitzering.domain.categories.GetCategoryTypesUseCase
import com.schweitzering.domain.transaction.AddTransactionUseCase
import com.schweitzering.walletmanager.transaction.TransactionActivity
import com.schweitzering.walletmanager.transaction.TransactionViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module{

    scope(named<TransactionActivity>()) {
        scoped { TransactionViewModel(get(), get()) }
    }

    single {GetCategoryTypesUseCase(get())}

    single {AddTransactionUseCase(get())}
}