package com.schweitzering.walletmanager.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.schweitzering.walletmanager.R
import com.schweitzering.walletmanager.databinding.ActivityMainBinding
import com.schweitzering.walletmanager.transaction.TransactionActivity
import com.schweitzering.walletmanager.utils.DataBindingProtocol
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.currentScope

class MainActivity : AppCompatActivity(), DataBindingProtocol {

    private val viewModel: MainViewModel by currentScope.inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDataBinding()

        observeFlowChanges()
    }

    private fun observeFlowChanges() {
        viewModel.state.observe(this, Observer {
            when(it) {
                MainViewModel.FlowState.NewIncome -> startActivity(TransactionActivity.getIncomeIntent(this))
                MainViewModel.FlowState.NewExpense -> startActivity(TransactionActivity.getExpenseIntent(this))
                MainViewModel.FlowState.NewSaving -> startActivity(TransactionActivity.getSavingIntent(this))
                MainViewModel.FlowState.NewInvestment -> startActivity(TransactionActivity.getInvestmentIntent(this))
            }
        })
    }

    override fun setDataBinding() {
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.viewModel = viewModel
    }
}
