package com.schweitzering.walletmanager.settings.categories.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.schweitzering.domain.ActionResponse
import com.schweitzering.domain.categories.TransactionCategory
import com.schweitzering.walletmanager.R
import com.schweitzering.walletmanager.databinding.ActivityCategoriesSettingsBinding
import com.schweitzering.walletmanager.settings.categories.create.CRUDCategoryActivity
import com.schweitzering.walletmanager.xsupport.utils.DataBindingProtocol
import kotlinx.android.synthetic.main.activity_categories_settings.*
import org.koin.androidx.scope.currentScope

class CategoriesSettingsActivity : AppCompatActivity(), DataBindingProtocol {

    companion object {
        fun getIntent(context: Context) = Intent(context, CategoriesSettingsActivity::class.java)
    }

    private val viewModel: CategoriesSettingsViewModel by currentScope.inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDataBinding()
        observeCategories()
        observeState()
    }

    private fun observeState() {
        viewModel.state.observe(this, Observer {
            when (it) {
                is CategoriesSettingsViewModel.State.CreateCategory -> startActivity(
                    CRUDCategoryActivity.getCreateIntent(this, it.type))
                is CategoriesSettingsViewModel.State.EditCategory -> startActivity(
                    CRUDCategoryActivity.getUpdateIntent(this, it.category)
                )
                is CategoriesSettingsViewModel.State.DeleteCategory ->
                    MaterialAlertDialogBuilder(this)
                        .setMessage(getString(R.string.delete_category_message))
                        .setPositiveButton(getString(R.string.default_confirmation)){ _, _-> deleteCategory(it.category)}
                        .setNegativeButton(getString(R.string.default_negation)){ _, _->}
                        .show()
            }
        })
    }

    private fun deleteCategory(category: TransactionCategory) {
        viewModel.deleteCategory(category).observe(this, Observer {
            when(it) {
                is ActionResponse.Correct ->
                    MaterialAlertDialogBuilder(this)
                        .setMessage(getString(R.string.delete_category_ok_message))
                        .setPositiveButton(getString(R.string.ok_confirmation)){ _, _-> finish()}
                        .show()
                is ActionResponse.CannotDeleteError ->
                    MaterialAlertDialogBuilder(this)
                        .setMessage(getString(R.string.cannot_delete_category_message))
                        .setPositiveButton(getString(R.string.ok_confirmation)){ _, _-> }
                        .show()
            }
        })
    }

    private fun observeCategories() {
        viewModel.categories.observe(this, Observer {
            with(list_categories) {
                adapter = CategoriesSettingsAdapter(it, viewModel)
                layoutManager = LinearLayoutManager(this@CategoriesSettingsActivity)
            }
        })
    }

    override fun setDataBinding() {
        val binding = DataBindingUtil.setContentView<ActivityCategoriesSettingsBinding>(this,
            R.layout.activity_categories_settings)
        binding.viewModel = viewModel
    }
}
