package com.schweitzering.walletmanager.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.schweitzering.walletmanager.R
import com.schweitzering.walletmanager.databinding.ItemTransactionBinding
import com.schweitzering.walletmanager.transaction.TransactionProfile


class TransactionsAdapter(private val transactionsList: List<TransactionProfile>,
                          private val viewModel: MainViewModel
): RecyclerView.Adapter<TransactionsAdapter.TransactionsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding: ItemTransactionBinding = DataBindingUtil.inflate(inflater, R.layout.item_transaction, parent, false)
        return TransactionsViewHolder(
            itemBinding,
            viewModel
        )
    }

    override fun getItemCount() = transactionsList.size


    override fun onBindViewHolder(holder: TransactionsViewHolder, position: Int) {
        holder.bind(transactionsList[position])
    }


    class TransactionsViewHolder(val binding: ItemTransactionBinding,
                                 val viewModel: MainViewModel
    ): RecyclerView.ViewHolder(binding.root) {

        lateinit var transaction: TransactionProfile

        fun bind(transaction: TransactionProfile) {
            binding.viewModel = viewModel
            binding.viewHolder = this
            this.transaction = transaction
        }
    }
}