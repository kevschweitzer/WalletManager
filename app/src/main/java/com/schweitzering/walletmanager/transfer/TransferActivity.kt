package com.schweitzering.walletmanager.transfer

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.schweitzering.walletmanager.R
import com.schweitzering.walletmanager.databinding.ActivityTransferBinding
import com.schweitzering.walletmanager.xsupport.utils.DataBindingProtocol
import org.koin.androidx.scope.currentScope

class TransferActivity : AppCompatActivity(), DataBindingProtocol {

    companion object {
        fun getIntent(context: Context) = Intent(context, TransferActivity::class.java)
    }

    private val viewModel: TransferViewModel by currentScope.inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDataBinding()
    }

    override fun setDataBinding() {
        val itemBinding = DataBindingUtil.setContentView<ActivityTransferBinding>(this, R.layout.activity_transfer)
        itemBinding.viewModel = viewModel
    }
}
