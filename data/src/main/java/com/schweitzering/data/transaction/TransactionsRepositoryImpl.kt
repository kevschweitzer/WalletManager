package com.schweitzering.data.transaction

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.schweitzering.data.xsupport.mappers.toTransaction
import com.schweitzering.data.xsupport.mappers.toTransactionEntity
import com.schweitzering.domain.transaction.Transaction
import com.schweitzering.domain.transaction.TransactionsRepository
import java.sql.Timestamp

class TransactionsRepositoryImpl(private val databaseManager: TransactionDatabaseManager): TransactionsRepository {

    override fun insert(model: Transaction) {
        databaseManager.insert(model.toTransactionEntity())
    }

    override fun delete(model: Transaction) {
        databaseManager.delete(model.toTransactionEntity())
    }

    override fun getAll() = Transformations.map(databaseManager.getAll()) { list ->
           list.map { it.toTransaction()}
    }

    override fun update(model: Transaction) {
        databaseManager.update(model.toTransactionEntity())
    }

    override fun getBetween(initialDate: Timestamp,
                            finalDate: Timestamp): LiveData<List<Transaction>> {
        return Transformations.map(databaseManager.getBetween(initialDate, finalDate)) { list ->
            list.map { it.toTransaction() }
        }
    }

    override fun getByAccount(accountId: Int) =
        Transformations.map(databaseManager.getByAccount(accountId)) {
            it.map { it.toTransaction() }
        }
}