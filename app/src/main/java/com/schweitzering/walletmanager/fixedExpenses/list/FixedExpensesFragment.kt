package com.schweitzering.walletmanager.fixedExpenses.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.schweitzering.walletmanager.R
import com.schweitzering.walletmanager.databinding.FragmentFixedExpensesBinding
import com.schweitzering.walletmanager.fixedExpenses.FixedExpenseProfile
import com.schweitzering.walletmanager.fixedExpenses.generator.create.NewFixedExpenseGeneratorActivity
import com.schweitzering.walletmanager.fixedExpenses.generator.list.FixedExpensesGeneratorsActivity
import com.schweitzering.walletmanager.fixedExpenses.utils.PayFixedExpenseDialog
import kotlinx.android.synthetic.main.fragment_fixed_expenses.*
import org.koin.androidx.scope.currentScope

class FixedExpensesFragment : Fragment() {

    private val viewModel: FixedExpensesViewModel by lazy { requireActivity().currentScope.get<FixedExpensesViewModel>() }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val itemBinding: FragmentFixedExpensesBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_fixed_expenses, container, false)
        itemBinding.viewModel = viewModel
        return itemBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeFlowState()
        observeFixedExpenses()
    }

    private fun observeFixedExpenses() {
        viewModel.fixedExpenses.observe(this, Observer {
            fixed_expenses_list.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = FixedExpensesAdapter(it, viewModel)
            }
        })
    }

    private fun observeFlowState() {
        viewModel.state.observe(this, Observer {
            when (it) {
                is FixedExpensesViewModel.FlowState.NewExepenseClicked ->
                    startActivity(NewFixedExpenseGeneratorActivity.getIntent(requireContext()))
                is FixedExpensesViewModel.FlowState.ShowGeneratorsClicked ->
                    startActivity(FixedExpensesGeneratorsActivity.getIntent(requireContext()))
                is FixedExpensesViewModel.FlowState.PayFixedExpenseClicked ->
                    showPayFixedExpenseDialog(it.fixedExpense)
            }
        })
    }

    private fun showPayFixedExpenseDialog(fixedExpense: FixedExpenseProfile) {
        PayFixedExpenseDialog.newInstance(requireFragmentManager(), fixedExpense)
    }
}