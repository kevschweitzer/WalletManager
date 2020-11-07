package com.schweitzering.walletmanager.transfer

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.schweitzering.domain.ActionResponse
import com.schweitzering.walletmanager.R
import com.schweitzering.walletmanager.databinding.ActivityTransferBinding
import com.schweitzering.walletmanager.xsupport.utils.DataBindingProtocol
import kotlinx.android.synthetic.main.activity_transfer.*
import org.koin.androidx.scope.currentScope

class TransferActivity : AppCompatActivity(), DataBindingProtocol {

    companion object {
        fun getIntent(context: Context) = Intent(context, TransferActivity::class.java)
    }

    private val viewModel: TransferViewModel by currentScope.inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDataBinding()

        observeState()
        observeAccounts()
        observeAccountsSelection()
    }

    private fun observeState() {
        viewModel.state.observe(this, Observer {
           newTransfer()
        })
    }

    private fun newTransfer() {
        viewModel.newTransfer().observe(this, Observer {
            when(it) {
                ActionResponse.Correct -> finish()
                ActionResponse.NotEnoughMoney -> MaterialAlertDialogBuilder(this@TransferActivity)
                    .setMessage(getString(R.string.not_enough_money_transfer))
                    .setPositiveButton(getString(R.string.ok_confirmation)){ _, _-> }
                    .show()
                else -> MaterialAlertDialogBuilder(this@TransferActivity)
                    .setMessage(getString(R.string.unknown_error))
                    .setPositiveButton(getString(R.string.ok_confirmation)){ _, _-> }
                    .show()
            }
        })
    }

    private fun observeAccounts() {
        viewModel.accounts.observe(this, Observer {
            addItemsToSpinner(spinner_destination_account, it.map { it.name })
            addItemsToSpinner(spinner_origin_account, it.map { it.name })
        })
    }

    private fun observeAccountsSelection() {
        spinner_origin_account.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?,
                                        view: View?,
                                        position: Int,
                                        id: Long) {
                viewModel.selectedOriginPosition = position
            }
        }
        spinner_destination_account.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?,
                                        view: View?,
                                        position: Int,
                                        id: Long) {
                viewModel.selectedDestinationPosition = position
            }
        }
    }

    override fun setDataBinding() {
        val itemBinding = DataBindingUtil.setContentView<ActivityTransferBinding>(this, R.layout.activity_transfer)
        itemBinding.viewModel = viewModel
    }

    private fun addItemsToSpinner(spinner: Spinner, items: List<String>) {
        val arrayAdapter =
            ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, items)
        spinner.adapter = arrayAdapter
    }
}
