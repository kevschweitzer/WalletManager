package com.schweitzering.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.schweitzering.data.transaction.TransactionsDao
import com.schweitzering.data.transaction.TransactionEntity
import com.schweitzering.data.balance.BalanceDao
import com.schweitzering.data.balance.BalanceEntity
import com.schweitzering.data.utils.Converters

@Database(entities = [BalanceEntity::class, TransactionEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun balanceDao(): BalanceDao
    abstract fun transactionsDao(): TransactionsDao
}