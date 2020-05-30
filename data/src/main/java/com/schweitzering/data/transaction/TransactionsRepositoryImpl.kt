package com.schweitzering.data.transaction

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.schweitzering.data.xsupport.mappers.toTransaction
import com.schweitzering.data.xsupport.mappers.toTransactionEntity
import com.schweitzering.domain.transaction.Transaction
import com.schweitzering.domain.transaction.TransactionType
import com.schweitzering.domain.transaction.TransactionsRepository
import java.time.LocalDate

class TransactionsRepositoryImpl(private val databaseManager: TransactionDatabaseManager): TransactionsRepository {

    override fun add(entity: Transaction) {
        databaseManager.insert(entity.toTransactionEntity())
    }

    override fun remove(entity: Transaction) {
        databaseManager.delete(entity.toTransactionEntity())
    }

    override fun getAll(): LiveData<List<Transaction>> {
       return Transformations.map(databaseManager.getAll()) { list ->
           list.map { it.toTransaction() }
       }
    }

    override fun getBetween(initialDate: LocalDate, finalDate: LocalDate): LiveData<List<Transaction>> {
        return Transformations.map(databaseManager.getBetween(initialDate, finalDate)) { list ->
            list.map { it.toTransaction() }
        }
    }

    /*override fun getByCategory(type: TransactionType): LiveData<List<Transaction>> {
        return Transformations.map(databaseManager.getByCategory(type)) { list ->
            list.map { it.toTransaction() }
        }
    }

    override fun getByCategoryType(type: String): LiveData<List<Transaction>> {
        return Transformations.map(databaseManager.getByCategoryType(type)) { list ->
            list.map { it.toTransaction() }
        }
    }*/
}