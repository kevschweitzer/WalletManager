package com.schweitzering.data.xsupport.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.schweitzering.data.transaction.TransactionsDao
import com.schweitzering.data.transaction.TransactionEntity
import com.schweitzering.data.balance.BalanceDao
import com.schweitzering.data.balance.BalanceEntity
import com.schweitzering.data.categorytypes.CategoryTypeEntity
import com.schweitzering.data.categorytypes.CategoryTypesDao
import com.schweitzering.data.fixedExpenses.FixedExpenseEntity
import com.schweitzering.data.fixedExpenses.FixedExpensesDao
import com.schweitzering.data.xsupport.utils.Converters

@Database(entities = [BalanceEntity::class, TransactionEntity::class,
    CategoryTypeEntity::class, FixedExpenseEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun balanceDao(): BalanceDao
    abstract fun transactionsDao(): TransactionsDao
    abstract fun categoryTypesDao(): CategoryTypesDao
    abstract fun fixedExpensesDao(): FixedExpensesDao
}