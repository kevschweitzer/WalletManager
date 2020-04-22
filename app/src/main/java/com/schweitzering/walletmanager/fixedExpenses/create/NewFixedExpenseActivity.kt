package com.schweitzering.walletmanager.fixedExpenses.create

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.schweitzering.walletmanager.R
import com.schweitzering.walletmanager.databinding.ActivityNewFixedExpenseBinding
import com.schweitzering.walletmanager.xsupport.utils.DataBindingProtocol
import org.koin.androidx.scope.currentScope

class NewFixedExpenseActivity : AppCompatActivity(), DataBindingProtocol {

    private val viewModel: NewFixedExpenseViewModel by currentScope.inject()

    companion object {
        fun getIntent(context: Context) = Intent(context, NewFixedExpenseActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDataBinding()
    }

    override fun setDataBinding() {
        val binding = DataBindingUtil.setContentView<ActivityNewFixedExpenseBinding>(this, R.layout.activity_new_fixed_expense)
        binding.viewModel = viewModel
    }
}
