package com.schweitzering.data.xsupport.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.schweitzering.data.balance.BalanceDao
import com.schweitzering.data.balance.BalanceEntity
import com.schweitzering.data.categories.TransactionCategoryDao
import com.schweitzering.data.categories.TransactionCategoryEntity
import com.schweitzering.data.debts.DebtEntity
import com.schweitzering.data.debts.DebtsDao
import com.schweitzering.data.fixedExpenses.FixedExpenseEntity
import com.schweitzering.data.fixedExpenses.FixedExpensesDao
import com.schweitzering.data.fixedExpenses.generator.FixedExpenseGeneratorEntity
import com.schweitzering.data.fixedExpenses.generator.FixedExpensesGeneratorsDao
import com.schweitzering.data.transaction.TransactionEntity
import com.schweitzering.data.transaction.TransactionsDao
import com.schweitzering.data.xsupport.utils.Converters
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(entities = [BalanceEntity::class, TransactionEntity::class, TransactionCategoryEntity::class, FixedExpenseEntity::class, FixedExpenseGeneratorEntity::class, DebtEntity::class],
    version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun balanceDao(): BalanceDao
    abstract fun transactionsDao(): TransactionsDao
    abstract fun transactionCategoriesDao(): TransactionCategoryDao
    abstract fun fixedExpensesDao(): FixedExpensesDao
    abstract fun fixedExpensesGeneratorDao(): FixedExpensesGeneratorsDao
    abstract fun debtsDao(): DebtsDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, "wallet-manager")
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        GlobalScope.launch {
                            TransactionCategoryEntity.getPredefinedCategories().forEach {
                                getInstance(context).transactionCategoriesDao().insert(it)
                            }

                        }
                    }
                }).build()

    }

}