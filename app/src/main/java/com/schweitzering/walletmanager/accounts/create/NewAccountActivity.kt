package com.schweitzering.walletmanager.accounts.create

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.schweitzering.walletmanager.R
import com.schweitzering.walletmanager.databinding.ActivityNewAccountBinding
import com.schweitzering.walletmanager.xsupport.utils.DataBindingProtocol
import org.koin.androidx.scope.currentScope

class NewAccountActivity : AppCompatActivity(), DataBindingProtocol {

    companion object {
        fun getIntent(context: Context) = Intent(context, NewAccountActivity::class.java)
    }

    private val viewModel: NewAccountViewModel by currentScope.inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDataBinding()
        observeState()
    }

    private fun observeState() {
        viewModel.state.observe(this, Observer {
            finish()
        })
    }

    override fun setDataBinding() {
        val binding = DataBindingUtil.setContentView<ActivityNewAccountBinding>(this,
            R.layout.activity_new_account)
        binding.viewModel = viewModel
    }
}
