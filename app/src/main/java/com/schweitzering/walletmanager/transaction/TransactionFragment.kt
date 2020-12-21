package com.schweitzering.walletmanager.transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.schweitzering.walletmanager.R
import com.schweitzering.walletmanager.commons.BaseTransactionViewModel
import com.schweitzering.walletmanager.commons.addItemsToSpinner
import com.schweitzering.walletmanager.databinding.FragmentTransactionBinding
import kotlinx.android.synthetic.main.fragment_transaction.*
import org.koin.android.viewmodel.ext.android.sharedViewModel


class TransactionFragment(
    private val viewModel: BaseTransactionViewModel
): Fragment() {

    private var itemBinding: FragmentTransactionBinding? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        itemBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_transaction, container, false)
        itemBinding?.viewModel = viewModel
        return itemBinding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeAccountSelection()
        observeCategorySelection()
        observeOptions()
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
        viewModel.categories.observe(this, Observer { addItemsToSpinner(requireContext(), spinner_category, it.map { it.name }) })
        viewModel.accounts?.observe(this, Observer { addItemsToSpinner(requireContext(), spinner_account, it.map { it.name }) })
    }

    override fun onDestroy() {
        itemBinding = null
        super.onDestroy()
    }
}