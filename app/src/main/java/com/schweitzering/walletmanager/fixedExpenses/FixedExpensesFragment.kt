package com.schweitzering.walletmanager.fixedExpenses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.schweitzering.walletmanager.R
import com.schweitzering.walletmanager.databinding.FragmentFixedExpensesBinding
import org.koin.androidx.scope.currentScope

class FixedExpensesFragment: Fragment() {

    private val viewModel: FixedExpensesViewModel by lazy { requireActivity().currentScope.get<FixedExpensesViewModel>() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val itemBinding: FragmentFixedExpensesBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_fixed_expenses, container, false)
        return itemBinding.root
    }
}