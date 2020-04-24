package com.schweitzering.walletmanager.fixedExpenses.generator

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.schweitzering.walletmanager.R
import com.schweitzering.walletmanager.databinding.ActivityNewFixedExpenseBinding
import com.schweitzering.walletmanager.xsupport.utils.DataBindingProtocol
import org.koin.androidx.scope.currentScope

class NewFixedExpenseGeneratorActivity : AppCompatActivity(), DataBindingProtocol {

    private val generatorViewModel: NewFixedExpenseGeneratorViewModel by currentScope.inject()

    companion object {
        fun getIntent(context: Context) = Intent(context, NewFixedExpenseGeneratorActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDataBinding()

        observeState()
    }

    private fun observeState() {
        generatorViewModel.state.observe(this, Observer {
            finish()
        })
    }

    override fun setDataBinding() {
        val binding = DataBindingUtil.setContentView<ActivityNewFixedExpenseBinding>(this, R.layout.activity_new_fixed_expense)
        binding.viewModel = generatorViewModel
    }
}
