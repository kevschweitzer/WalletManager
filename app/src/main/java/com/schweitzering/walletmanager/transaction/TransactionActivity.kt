package com.schweitzering.walletmanager.transaction

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.schweitzering.domain.transaction.TransactionType
import com.schweitzering.walletmanager.R
import com.schweitzering.walletmanager.databinding.ActivityTransactionBinding
import com.schweitzering.walletmanager.xsupport.utils.Constants.Companion.TRANSACTION_CATEGORY
import com.schweitzering.walletmanager.xsupport.utils.DataBindingProtocol
import kotlinx.android.synthetic.main.activity_transaction.*
import org.koin.androidx.scope.currentScope

class TransactionActivity : AppCompatActivity(), DataBindingProtocol {

    private val viewModel: TransactionViewModel by currentScope.inject()

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

        observeState()
        observeOptions()
        observeCategorySelection()
        observeAccountSelection()
    }

    private fun observeAccountSelection() {
        spinner_account.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?,
                                        view: View?,
                                        position: Int,
                                        id: Long) {
                viewModel.selectedAccountPosition = position
            }
        }
    }

    private fun observeState() {
        viewModel.state.observe(this, Observer {
            when (it) {
                TransactionViewModel.TransactionState.Finished -> finish()
            }
        })
    }

    private fun observeCategorySelection() {
        spinner_category.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?,
                                        view: View?,
                                        position: Int,
                                        id: Long) {
                viewModel.selectedCategoryPosition = position
            }
        }
    }

    private fun observeOptions() {
        viewModel.categories.observe(this, Observer { addItemsToSpinner(spinner_category, it.map { it.name }) })
        viewModel.accounts.observe(this, Observer { addItemsToSpinner(spinner_account, it.map { it.name }) })
    }

    override fun setDataBinding() {
        val binding = DataBindingUtil.setContentView<ActivityTransactionBinding>(this,
            R.layout.activity_transaction)
        binding.viewModel = viewModel
    }

    private fun addItemsToSpinner(spinner: Spinner, items: List<String>) {
        val arrayAdapter =
            ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, items)
        spinner.adapter = arrayAdapter
    }
}
