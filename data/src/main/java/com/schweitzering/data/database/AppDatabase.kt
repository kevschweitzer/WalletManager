package com.schweitzering.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.schweitzering.data.transaction.Converters
import com.schweitzering.data.transaction.expense.ExpenseDao
import com.schweitzering.data.transaction.expense.ExpenseEntity
import com.schweitzering.data.transaction.income.IncomeDao
import com.schweitzering.data.transaction.income.IncomeEntity

@Database(entities = [IncomeEntity::class, ExpenseEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {


    abstract fun incomeDao(): IncomeDao
    abstract fun expenseDao(): ExpenseDao
}