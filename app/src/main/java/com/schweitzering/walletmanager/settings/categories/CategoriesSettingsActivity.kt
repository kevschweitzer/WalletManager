package com.schweitzering.walletmanager.settings.categories

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.schweitzering.walletmanager.R
import com.schweitzering.walletmanager.databinding.ActivityCategoriesSettingsBinding
import com.schweitzering.walletmanager.databinding.ActivitySettingsBinding
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
        val binding = DataBindingUtil.setContentView<ActivityCategoriesSettingsBinding>(this, R.layout.activity_categories_settings)
        binding.viewModel = viewModel
    }
}
