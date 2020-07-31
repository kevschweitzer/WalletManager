package com.schweitzering.walletmanager.fixedExpenses.utils

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.schweitzering.walletmanager.R
import com.schweitzering.walletmanager.databinding.DialogPayFixedExpenseBinding
import com.schweitzering.walletmanager.fixedExpenses.FixedExpenseProfile
import com.schweitzering.walletmanager.fixedExpenses.list.FixedExpensesViewModel
import org.koin.androidx.scope.currentScope
import java.lang.IllegalStateException

class PayFixedExpenseDialog(private val fixedExpense: FixedExpenseProfile): DialogFragment() {

    private lateinit var itemBinding: DialogPayFixedExpenseBinding
    private val viewModel: FixedExpensesViewModel by lazy { requireActivity().currentScope.get<FixedExpensesViewModel>() }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let{
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            itemBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_pay_fixed_expense, null, false)
            itemBinding.fixedExpense = fixedExpense
            builder.setView(itemBinding.root)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    companion object {
        fun newInstance(fragmentManager: FragmentManager, fixedExpense: FixedExpenseProfile) {
            PayFixedExpenseDialog(fixedExpense).show(fragmentManager, "")
        }
    }


}