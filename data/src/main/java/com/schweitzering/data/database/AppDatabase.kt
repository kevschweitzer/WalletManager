package com.schweitzering.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.schweitzering.data.finance.FinanceDao
import com.schweitzering.data.finance.FinanceEntity
import com.schweitzering.data.balance.BalanceDao
import com.schweitzering.data.balance.BalanceEntity
import com.schweitzering.data.utils.Converters

@Database(entities = [BalanceEntity::class, FinanceEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun balanceDao(): BalanceDao
    abstract fun financeDao(): FinanceDao
}