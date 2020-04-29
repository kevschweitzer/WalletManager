package com.schweitzering.walletmanager.debts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.schweitzering.walletmanager.R
import com.schweitzering.walletmanager.databinding.FragmentDebtsBinding
import com.schweitzering.walletmanager.debts.create.NewDebtActivity
import kotlinx.android.synthetic.main.fragment_debts.*
import org.koin.androidx.scope.currentScope

class DebtsFragment: Fragment() {

    private val viewModel: DebtsViewModel by lazy {requireActivity().currentScope.get<DebtsViewModel>()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDebtsList()
        observeFlowState()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val itemBinding: FragmentDebtsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_debts, container, false)
        itemBinding.viewModel = viewModel
        itemBinding.lifecycleOwner = this
        return itemBinding.root
    }

    private fun observeFlowState() {
        viewModel.state.observe(this, Observer {
            when(it) {
                is DebtsViewModel.FlowState.NewDebtClicked -> startActivity(NewDebtActivity.getIntent(requireContext()))
            }
        })
    }

    private fun setDebtsList() {
        viewModel.debts.observe(this, Observer {
            list_debts.apply {
                adapter = DebtsAdapter(it,viewModel)
                layoutManager = LinearLayoutManager(requireContext())
            }
        })
    }
}