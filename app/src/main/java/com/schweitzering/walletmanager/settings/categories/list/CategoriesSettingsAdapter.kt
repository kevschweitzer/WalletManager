package com.schweitzering.walletmanager.settings.categories.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.schweitzering.domain.categories.TransactionCategory
import com.schweitzering.walletmanager.R
import com.schweitzering.walletmanager.databinding.ItemCategoryTypeBinding


class CategoriesSettingsAdapter(private val categoriesList: List<TransactionCategory>,
                                private val viewModel: CategoriesSettingsViewModel) :
    RecyclerView.Adapter<CategoriesSettingsAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding: ItemCategoryTypeBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_category_type, parent, false)
        return CategoryViewHolder(itemBinding, viewModel)
    }

    override fun getItemCount() = categoriesList.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categoriesList[position])
    }

    class CategoryViewHolder(val binding: ItemCategoryTypeBinding,
                             val viewModel: CategoriesSettingsViewModel) :
        RecyclerView.ViewHolder(binding.root) {

        lateinit var transactionCategory: TransactionCategory

        fun bind(transactionCategory: TransactionCategory) {
            binding.viewModel = viewModel
            binding.viewHolder = this
            this.transactionCategory = transactionCategory
        }
    }
}