package com.schweitzering.walletmanager.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.schweitzering.walletmanager.R
import com.schweitzering.walletmanager.databinding.ActivityMainBinding
import com.schweitzering.walletmanager.transaction.TransactionActivity
import com.schweitzering.walletmanager.utils.DataBindingProtocol
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.currentScope

class MainActivity : AppCompatActivity(), DataBindingProtocol {

    private val viewModel: MainViewModel by currentScope.inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDataBinding()

        observeFlowChanges()
        observeLastTransactions()
    }

    private fun observeLastTransactions() {
        viewModel.lastTransactions.observe(this, Observer {
            transaction_list.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = TransactionsAdapter(it, viewModel)
            }
        })
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
        binding.lifecycleOwner = this
    }
}
