package com.schweitzering.walletmanager.transaction

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.schweitzering.domain.ActionResponse
import com.schweitzering.domain.transaction.TransactionType
import com.schweitzering.walletmanager.R
import com.schweitzering.walletmanager.databinding.ActivityTransactionBinding
import com.schweitzering.walletmanager.xsupport.utils.Constants.Companion.TRANSACTION_CATEGORY
import com.schweitzering.walletmanager.xsupport.utils.DataBindingProtocol
import org.koin.android.viewmodel.ext.android.viewModel

class TransactionActivity : AppCompatActivity(), DataBindingProtocol {

    private val viewModel: TransactionViewModel by viewModel()

    companion object {
        fun getExpenseIntent(context: Context): Intent {
            val intent = Intent(context, TransactionActivity::class.java)
            intent.putExtra(TRANSACTION_CATEGORY, TransactionType.EXPENSE)
            return intent
        }

        fun getIncomeIntent(context: Context): Intent {
            val intent = Intent(context, TransactionActivity::class.java)
            intent.putExtra(TRANSACTION_CATEGORY, TransactionType.INCOME)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDataBinding()
        viewModel.handleIntent(intent)
        initView()
        observeState()
    }

    private fun initView() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val fragment = TransactionFragment(viewModel)
        fragmentTransaction.add(R.id.transaction_container, fragment)
        fragmentTransaction.commit()
    }

    private fun observeState() {
        viewModel.state.observe(this, Observer {
            when(it) {
                TransactionViewModel.TransactionState.ContinueClicked -> addTransaction()
            }
        })
    }

    private fun addTransaction() {
        viewModel.addTransaction().observe(this, Observer {
            when(it) {
                ActionResponse.Correct -> finish()
                ActionResponse.NotEnoughMoney -> MaterialAlertDialogBuilder(this@TransactionActivity)
                    .setMessage(getString(R.string.not_enough_money_transaction))
                    .setPositiveButton(getString(R.string.ok_confirmation)){ _, _-> }
                    .show()
                else -> MaterialAlertDialogBuilder(this@TransactionActivity)
                    .setMessage(getString(R.string.unknown_error))
                    .setPositiveButton(getString(R.string.ok_confirmation)){ _, _-> }
                    .show()
            }
        })
    }

    override fun setDataBinding() {
        val binding = DataBindingUtil.setContentView<ActivityTransactionBinding>(this,
            R.layout.activity_transaction)
        binding.viewModel = viewModel
    }
}
