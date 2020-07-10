package com.schweitzering.data.transfer

import androidx.lifecycle.Transformations
import com.schweitzering.data.xsupport.mappers.toAccount
import com.schweitzering.domain.tranfer.Transfer
import com.schweitzering.domain.tranfer.TransferRepository
import kotlinx.coroutines.runBlocking

class TransferRepositoryImpl(private val transferDao: TransferDao): TransferRepository {

    override fun insert(model: Transfer) {
        runBlocking {
            transferDao.insert(model.toTransferEntity())
        }
    }

    override fun delete(model: Transfer) {
        runBlocking {
            transferDao.delete(model.toTransferEntity())
        }
    }

    override fun update(model: Transfer) {
        runBlocking {
            transferDao.update(model.toTransferEntity())
        }
    }

    override fun getAll() = Transformations.map(transferDao.getAll()) {
        it.map { it.toTransfer() }
    }
}

fun Transfer.toTransferEntity() = TransferEntity(
    id,
    value,
    description,
    date,
    originAccount.id,
    destinationAccount.id
)

fun TransferWithAccounts.toTransfer() = Transfer(
    transfer.id,
    transfer.value,
    transfer.description,
    transfer.date,
    originAccount.toAccount(),
    destinationAccount.toAccount()
)