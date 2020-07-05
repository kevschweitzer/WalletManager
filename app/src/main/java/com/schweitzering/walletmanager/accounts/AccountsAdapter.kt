package com.schweitzering.walletmanager.accounts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.schweitzering.domain.accounts.Account
import com.schweitzering.walletmanager.R
import com.schweitzering.walletmanager.databinding.ItemAccountBinding

class AccountsAdapter(private val accounts: List<Account>,
                      private val viewModel: AccountsViewModel):
    RecyclerView.Adapter<AccountsAdapter.AccountViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding: ItemAccountBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_account, parent, false)
        return AccountViewHolder(itemBinding, viewModel)
    }

    override fun getItemCount() = accounts.size

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        holder.bind(accounts[position])
    }

    class AccountViewHolder(private val binding: ItemAccountBinding,
                            private val viewModel: AccountsViewModel):
        RecyclerView.ViewHolder(binding.root) {

        lateinit var account: Account

        fun bind(account: Account) {
            this.account = account
            binding.viewModel = viewModel
            binding.viewHolder = this
        }
    }
}