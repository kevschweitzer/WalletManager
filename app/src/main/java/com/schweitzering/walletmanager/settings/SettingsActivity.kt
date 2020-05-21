package com.schweitzering.walletmanager.settings

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.schweitzering.walletmanager.R
import com.schweitzering.walletmanager.databinding.ActivitySettingsBinding
import com.schweitzering.walletmanager.settings.categories.CategoriesSettingsActivity
import com.schweitzering.walletmanager.xsupport.utils.DataBindingProtocol
import org.koin.androidx.scope.currentScope

class SettingsActivity : AppCompatActivity(), DataBindingProtocol {

    private val viewModel: SettingsViewModel by currentScope.inject()

    companion object {
        fun getIntent(context: Context) = Intent(context, SettingsActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDataBinding()
        observeState()
    }

    private fun observeState() {
        viewModel.state.observe(this, Observer {
            startActivity(CategoriesSettingsActivity.getIntent(this))
        })
    }

    override fun setDataBinding() {
        val binding = DataBindingUtil.setContentView<ActivitySettingsBinding>(this, R.layout.activity_settings)
        binding.viewModel = viewModel
    }
}
