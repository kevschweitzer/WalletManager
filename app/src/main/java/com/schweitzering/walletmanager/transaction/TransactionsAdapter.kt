package com.schweitzering.walletmanager.transaction

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.schweitzering.walletmanager.R


class TransactionsAdapter(val transactionsList: List<TransactionProfile>): RecyclerView.Adapter<TransactionsAdapter.TransactionsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TransactionsViewHolder(inflater.inflate(R.layout.item_transaction, parent, false))
    }

    override fun getItemCount() = transactionsList.size


    override fun onBindViewHolder(holder: TransactionsViewHolder, position: Int) {
        holder.bind(transactionsList[position])
    }


    class TransactionsViewHolder(val itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(transaction: TransactionProfile) {

        }
    }
}