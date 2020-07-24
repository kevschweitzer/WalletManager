package com.schweitzering.walletmanager.balance

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.schweitzering.domain.movements.Movement
import com.schweitzering.domain.tranfer.Transfer
import com.schweitzering.domain.transaction.Transaction
import com.schweitzering.walletmanager.R
import com.schweitzering.walletmanager.databinding.ItemTransactionBinding


class TransactionsAdapter(private val transactionsList: List<Movement>,
                          private val viewModel: BalanceViewModel) :
    RecyclerView.Adapter<TransactionsAdapter.TransactionsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding: ItemTransactionBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_transaction, parent, false)
        return TransactionsViewHolder(itemBinding, viewModel)
    }

    override fun getItemCount() = transactionsList.size


    override fun onBindViewHolder(holder: TransactionsViewHolder, position: Int) {
        holder.bind(transactionsList[position])
    }


    class TransactionsViewHolder(val binding: ItemTransactionBinding,
                                 val viewModel: BalanceViewModel) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movement: Movement) {
            binding.viewModel = viewModel
            binding.movement = movement
            setData(movement)
        }

        private fun setData(movement: Movement) {
            when(movement) {
                is Transaction -> {
                    binding.category.text = movement.category.name
                    binding.account.text = movement.account.name
                }
                is Transfer -> {
                    binding.category.text = binding.root.resources.getString(R.string.transfer_title)
                    binding.account.text =
                        binding.root.resources.getString(
                            R.string.transfer_description,
                            movement.originAccount.name,
                            movement.destinationAccount.name
                        )
                }
            }

        }
    }
}