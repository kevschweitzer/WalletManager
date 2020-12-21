package com.schweitzering.walletmanager.debts

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import com.google.android.gms.common.ErrorDialogFragment
import com.schweitzering.domain.debts.Debt
import com.schweitzering.walletmanager.R
import com.schweitzering.walletmanager.databinding.DialogResolveDebtBinding
import org.koin.androidx.scope.currentScope

class ResolveDebtDialog(
    private val debt: Debt
): DialogFragment() {

    private var itemBinding: DialogResolveDebtBinding? = null
    private val viewModel: DebtsViewModel by lazy { requireActivity().currentScope.get<DebtsViewModel>() }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let{
            val inflater = requireActivity().layoutInflater
            itemBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_resolve_debt, null, false)
            setUpAccounts()
            with(AlertDialog.Builder(it)) {
                setView(itemBinding?.root)
                setPositiveButton("Pay") { _, _ ->
                    viewModel.selectedAccount?.let {
                        viewModel.resolveDebt(debt, it).observe(this@ResolveDebtDialog, Observer {
                            this@ResolveDebtDialog.dismiss()
                        })
                    }
                 }
                setNegativeButton("Cancel"){_, _ -> /*dismiss*/}
                create()
            }
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun setUpAccounts() {
        viewModel.accounts.observe(this, Observer {
            val arrayAdapter = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, it.map { it.name })
            itemBinding?.accountsList?.adapter = arrayAdapter
        })
    }

    companion object {
        fun newInstance(fragmentManager: FragmentManager, debt: Debt) {
            ResolveDebtDialog(debt).show(fragmentManager, "")
        }
    }
}