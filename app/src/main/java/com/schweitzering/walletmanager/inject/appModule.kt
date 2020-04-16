package com.schweitzering.walletmanager.inject

import com.schweitzering.walletmanager.expense.ExpenseActivity
import com.schweitzering.walletmanager.expense.ExpenseViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module{

    scope(named<ExpenseActivity>()) {
        scoped { ExpenseViewModel() }
    }
}