package com.schweitzering.walletmanager.debts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.schweitzering.domain.debts.Debt
import com.schweitzering.walletmanager.R
import com.schweitzering.walletmanager.databinding.ItemDebtBinding

class DebtsAdapter(private val debtsList: List<Debt>,
                   private val viewModel: DebtsViewModel) :
    RecyclerView.Adapter<DebtsAdapter.DebtViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DebtViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding: ItemDebtBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_debt, parent, false)
        return DebtViewHolder(itemBinding, viewModel)
    }

    override fun getItemCount() = debtsList.size

    override fun onBindViewHolder(holder: DebtViewHolder, position: Int) {
        holder.bind(debtsList[position])
    }

    class DebtViewHolder(val binding: ItemDebtBinding, val viewModel: DebtsViewModel) :
        RecyclerView.ViewHolder(binding.root) {

        lateinit var debt: Debt

        fun bind(debt: Debt) {
            binding.viewModel = viewModel
            binding.viewHolder = this
            this.debt = debt
        }
    }
}