package com.schweitzering.walletmanager.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.schweitzering.walletmanager.R
import com.schweitzering.walletmanager.transaction.TransactionActivity
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onNewExpenseClicked(view: View) {
        startActivity(TransactionActivity.getExpenseIntent(this))
    }

    fun onNewIncomeClicked(view: View) {
        startActivity(TransactionActivity.getIncomeIntent(this))
    }
}
