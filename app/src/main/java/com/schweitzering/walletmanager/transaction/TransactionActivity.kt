package com.schweitzering.walletmanager.transaction

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.schweitzering.domain.transaction.TransactionCategory
import com.schweitzering.walletmanager.xsupport.utils.Constants.Companion.TRANSACTION_CATEGORY
import com.schweitzering.walletmanager.R
import com.schweitzering.walletmanager.databinding.ActivityTransactionBinding
import com.schweitzering.walletmanager.xsupport.utils.DataBindingProtocol
import kotlinx.android.synthetic.main.activity_transaction.*
import org.koin.androidx.scope.currentScope

class TransactionActivity : AppCompatActivity(), DataBindingProtocol {

    private val viewModel: TransactionViewModel by currentScope.inject()

    companion object {
        fun getExpenseIntent(context: Context): Intent{
            val intent = Intent(context, TransactionActivity::class.java)
            intent.putExtra(TRANSACTION_CATEGORY, TransactionCategory.EXPENSE)
            return intent
        }
        fun getIncomeIntent(context: Context): Intent {
            val intent = Intent(context, TransactionActivity::class.java)
            intent.putExtra(TRANSACTION_CATEGORY, TransactionCategory.INCOME)
            return intent
        }
        fun getSavingIntent(context: Context): Intent {
            val intent = Intent(context, TransactionActivity::class.java)
            intent.putExtra(TRANSACTION_CATEGORY, TransactionCategory.SAVING)
            return intent
        }
        fun getInvestmentIntent(context: Context): Intent {
            val intent = Intent(context, TransactionActivity::class.java)
            intent.putExtra(TRANSACTION_CATEGORY, TransactionCategory.INVESTMENT)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDataBinding()
        viewModel.handleIntent(intent)

        observeState()
        observeCategories()
        observeCategorySelection()
    }

    private fun observeState() {
        viewModel.state.observe(this, Observer {
            when(it) {
                TransactionViewModel.TransactionState.Finished -> finish()
            }
        })
    }

    private fun observeCategorySelection() {
        spinner_category.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
               viewModel.selectedCategoryPosition = position
            }

        }
    }

    private fun observeCategories() {
        viewModel.categories.observe(this, Observer { addItemsToSpinner(it) })
    }

    override fun setDataBinding() {
        val binding = DataBindingUtil.setContentView<ActivityTransactionBinding>(this, R.layout.activity_transaction)
        binding.viewModel = viewModel
    }

    private fun addItemsToSpinner(categories: List<String>) {
        val arrayAdapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, categories)
        spinner_category.adapter = arrayAdapter
    }
}
