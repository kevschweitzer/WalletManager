package com.schweitzering.walletmanager.balance


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.schweitzering.walletmanager.R
import com.schweitzering.walletmanager.databinding.FragmentBalanceBinding
import com.schweitzering.walletmanager.transaction.TransactionActivity
import kotlinx.android.synthetic.main.fragment_balance.*
import org.koin.androidx.scope.currentScope

/**
 * A simple [Fragment] subclass.
 */
class BalanceFragment : Fragment() {

    private val viewModel: BalanceViewModel by lazy { requireActivity().currentScope.get<BalanceViewModel>() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeFlowChanges()
        observeLastTransactions()
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val itemBinding: FragmentBalanceBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_balance, container, false)
        itemBinding.viewModel = viewModel
        itemBinding.lifecycleOwner = this
        return itemBinding.root
    }

    private fun observeLastTransactions() {
        viewModel.lastTransactions.observe(this, Observer {
            transaction_list.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = TransactionsAdapter(it, viewModel)
            }
        })
    }

    private fun observeFlowChanges() {
        viewModel.state.observe(this, Observer {
            when (it) {
                BalanceViewModel.FlowState.NewIncome -> startActivity(TransactionActivity.getIncomeIntent(
                    requireContext()))
                BalanceViewModel.FlowState.NewExpense -> startActivity(TransactionActivity.getExpenseIntent(
                    requireContext()))
            }
        })
    }
}
