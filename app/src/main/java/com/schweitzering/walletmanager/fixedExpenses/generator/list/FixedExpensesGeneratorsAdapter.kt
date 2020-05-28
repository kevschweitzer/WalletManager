package com.schweitzering.walletmanager.fixedExpenses.generator.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.schweitzering.walletmanager.R
import com.schweitzering.walletmanager.databinding.ItemFixedExpenseGeneratorBinding
import com.schweitzering.walletmanager.fixedExpenses.generator.FixedExpenseGeneratorProfile


class FixedExpensesGeneratorsAdapter(private val fixedExpensesGeneratorsList: List<FixedExpenseGeneratorProfile>,
                                     private val viewModel: FixedExpensesGeneratorsViewModel) :
    RecyclerView.Adapter<FixedExpensesGeneratorsAdapter.FixedExpenseGeneratorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): FixedExpenseGeneratorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding: ItemFixedExpenseGeneratorBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_fixed_expense_generator, parent, false)
        return FixedExpenseGeneratorViewHolder(itemBinding, viewModel)
    }

    override fun getItemCount() = fixedExpensesGeneratorsList.size


    override fun onBindViewHolder(holder: FixedExpenseGeneratorViewHolder, position: Int) {
        holder.bind(fixedExpensesGeneratorsList[position])
    }


    class FixedExpenseGeneratorViewHolder(val binding: ItemFixedExpenseGeneratorBinding,
                                          val viewModel: FixedExpensesGeneratorsViewModel) :
        RecyclerView.ViewHolder(binding.root) {

        lateinit var fixedExpenseGenerator: FixedExpenseGeneratorProfile

        fun bind(fixedExpense: FixedExpenseGeneratorProfile) {
            binding.viewModel = viewModel
            binding.viewHolder = this
            this.fixedExpenseGenerator = fixedExpense
        }
    }
}