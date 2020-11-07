package com.schweitzering.walletmanager.fixedExpenses.utils

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import com.schweitzering.walletmanager.R
import com.schweitzering.walletmanager.databinding.DialogPayFixedExpenseBinding
import com.schweitzering.walletmanager.fixedExpenses.FixedExpenseProfile
import com.schweitzering.walletmanager.fixedExpenses.list.FixedExpensesViewModel
import org.koin.androidx.scope.currentScope

class PayFixedExpenseDialog(private val fixedExpense: FixedExpenseProfile): DialogFragment() {

    private lateinit var itemBinding: DialogPayFixedExpenseBinding
    private val viewModel: FixedExpensesViewModel by lazy { requireActivity().currentScope.get<FixedExpensesViewModel>() }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let{
            val inflater = requireActivity().layoutInflater
            itemBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_pay_fixed_expense, null, false)
            itemBinding.fixedExpense = fixedExpense
            setUpAccounts()
            with(AlertDialog.Builder(it)) {
                setView(itemBinding.root)
                setPositiveButton("Pay") { _, _ -> viewModel.payFixedExpense(fixedExpense) }
                setNegativeButton("Cancel"){_, _ -> /*dismiss*/}
                create()
            }
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun setUpAccounts() {
        viewModel.accounts.observe(this, Observer {
            val arrayAdapter = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, it.map { it.name })
            itemBinding.accountsList.adapter = arrayAdapter
        })
        itemBinding.accountsList.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?,
                                        view: View?,
                                        position: Int,
                                        id: Long) {
                viewModel.accounts.value?.get(position)?.let {
                    fixedExpense.expense.account = it
                }
            }
        }
    }

    companion object {
        fun newInstance(fragmentManager: FragmentManager, fixedExpense: FixedExpenseProfile) {
            PayFixedExpenseDialog(fixedExpense).show(fragmentManager, "")
        }
    }
}