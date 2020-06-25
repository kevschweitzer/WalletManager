package com.schweitzering.data.transfer

import androidx.lifecycle.Transformations
import com.schweitzering.data.xsupport.mappers.toAccount
import com.schweitzering.domain.tranfer.Transfer
import com.schweitzering.domain.tranfer.TransferRepository
import kotlinx.coroutines.runBlocking

class TransferRepositoryImpl(private val transferDao: TransferDao): TransferRepository {

    override fun insert(transfer: Transfer) {
        runBlocking {
            transferDao.insert(transfer.toTransferEntity())
        }
    }

    override fun delete(transfer: Transfer) {
        runBlocking {
            transferDao.delete(transfer.toTransferEntity())
        }
    }

    override fun update(transfer: Transfer) {
        runBlocking {
            transferDao.update(transfer.toTransferEntity())
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