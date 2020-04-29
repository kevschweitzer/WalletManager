package com.schweitzering.walletmanager.debts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.schweitzering.walletmanager.R
import com.schweitzering.walletmanager.databinding.ItemDebtBinding

class DebtsAdapter(private val debtsList: List<DebtProfile>,
                          private val viewModel: DebtsViewModel
): RecyclerView.Adapter<DebtsAdapter.DebtViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DebtViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding: ItemDebtBinding = DataBindingUtil.inflate(inflater, R.layout.item_debt, parent, false)
        return DebtViewHolder(
            itemBinding,
            viewModel
        )
    }

    override fun getItemCount() = debtsList.size


    override fun onBindViewHolder(holder: DebtViewHolder, position: Int) {
        holder.bind(debtsList[position])
    }


    class DebtViewHolder(val binding: ItemDebtBinding,
                                 val viewModel: DebtsViewModel
    ): RecyclerView.ViewHolder(binding.root) {

        lateinit var debt: DebtProfile

        fun bind(debt: DebtProfile) {
            binding.viewModel = viewModel
            binding.viewHolder = this
            this.debt = debt
        }
    }
}