package com.schweitzering.walletmanager.fixedExpenses.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.schweitzering.walletmanager.R
import com.schweitzering.walletmanager.databinding.ItemFixedExpenseBinding
import com.schweitzering.walletmanager.fixedExpenses.FixedExpenseProfile


class FixedExpensesAdapter(private val fixedExpensesList: List<FixedExpenseProfile>,
                           private val viewModel: FixedExpensesViewModel) :
    RecyclerView.Adapter<FixedExpensesAdapter.FixedExpenseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FixedExpenseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding: ItemFixedExpenseBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_fixed_expense, parent, false)
        return FixedExpenseViewHolder(itemBinding, viewModel)
    }

    override fun getItemCount() = fixedExpensesList.size


    override fun onBindViewHolder(holder: FixedExpenseViewHolder, position: Int) {
        holder.bind(fixedExpensesList[position])
    }


    class FixedExpenseViewHolder(val binding: ItemFixedExpenseBinding,
                                 val viewModel: FixedExpensesViewModel) :
        RecyclerView.ViewHolder(binding.root) {

        lateinit var fixedExpense: FixedExpenseProfile

        fun bind(fixedExpense: FixedExpenseProfile) {
            binding.viewModel = viewModel
            binding.viewHolder = this
            this.fixedExpense = fixedExpense
        }
    }
}