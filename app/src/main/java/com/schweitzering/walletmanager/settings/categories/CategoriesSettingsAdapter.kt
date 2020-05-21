package com.schweitzering.walletmanager.settings.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.schweitzering.domain.categories.CategoryType
import com.schweitzering.walletmanager.R
import com.schweitzering.walletmanager.databinding.ItemCategoryTypeBinding
import com.schweitzering.walletmanager.databinding.ItemTransactionBinding
import com.schweitzering.walletmanager.settings.SettingsViewModel
import com.schweitzering.walletmanager.transaction.TransactionProfile


class CategoriesSettingsAdapter(private val categoriesList: List<CategoryType>,
                          private val viewModel: CategoriesSettingsViewModel
): RecyclerView.Adapter<CategoriesSettingsAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding: ItemCategoryTypeBinding = DataBindingUtil.inflate(inflater, R.layout.item_category_type, parent, false)
        return CategoryViewHolder(
            itemBinding,
            viewModel
        )
    }

    override fun getItemCount() = categoriesList.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categoriesList[position])
    }

    class CategoryViewHolder(val binding: ItemCategoryTypeBinding,
                             val viewModel: CategoriesSettingsViewModel): RecyclerView.ViewHolder(binding.root) {

        lateinit var category: CategoryType

        fun bind(category: CategoryType) {
            binding.viewModel = viewModel
            binding.viewHolder = this
            this.category = category
        }
    }
}