package com.schweitzering.walletmanager.fixedExpenses.generator.create

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.schweitzering.domain.transaction.TransactionType
import com.schweitzering.walletmanager.R
import com.schweitzering.walletmanager.databinding.ActivityNewFixedExpenseBinding
import com.schweitzering.walletmanager.transaction.TransactionFragment
import com.schweitzering.walletmanager.xsupport.utils.DataBindingProtocol
import org.koin.android.viewmodel.ext.android.viewModel

class NewFixedExpenseGeneratorActivity : AppCompatActivity(), DataBindingProtocol {

    private val generatorViewModel: NewFixedExpenseGeneratorViewModel by viewModel()

    companion object {
        fun getIntent(context: Context) = Intent(context, NewFixedExpenseGeneratorActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        generatorViewModel.transactionType = TransactionType.EXPENSE
        setDataBinding()
        initView()
        observeState()
    }

    private fun initView() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val fragment = TransactionFragment(generatorViewModel)
        fragmentTransaction.add(R.id.transaction_container, fragment)
        fragmentTransaction.commit()
    }

    private fun observeState() {
        generatorViewModel.state.observe(this, Observer {
            finish()
        })
    }

    override fun setDataBinding() {
        val binding = DataBindingUtil.setContentView<ActivityNewFixedExpenseBinding>(this,
            R.layout.activity_new_fixed_expense)
        binding.viewModel = generatorViewModel
    }
}
