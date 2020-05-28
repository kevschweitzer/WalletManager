package com.schweitzering.walletmanager.debts.create

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.schweitzering.walletmanager.R
import com.schweitzering.walletmanager.databinding.ActivityNewDebtBinding
import com.schweitzering.walletmanager.xsupport.utils.DataBindingProtocol
import org.koin.androidx.scope.currentScope

class NewDebtActivity : AppCompatActivity(), DataBindingProtocol {

    companion object {
        fun getIntent(context: Context) = Intent(context, NewDebtActivity::class.java)
    }

    private val viewModel: NewDebtViewModel by currentScope.inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDataBinding()
        observeFlow()
    }

    private fun observeFlow() {
        viewModel.state.observe(this, Observer {
            when (it) {
                NewDebtViewModel.FlowState.SuccessCreation -> finish()
            }
        })
    }

    override fun setDataBinding() {
        val binding =
            DataBindingUtil.setContentView<ActivityNewDebtBinding>(this, R.layout.activity_new_debt)
        binding.viewModel = viewModel
    }
}
