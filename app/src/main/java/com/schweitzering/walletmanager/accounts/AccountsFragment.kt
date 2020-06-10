package com.schweitzering.walletmanager.accounts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.schweitzering.walletmanager.R
import com.schweitzering.walletmanager.accounts.create.NewAccountActivity
import com.schweitzering.walletmanager.databinding.FragmentAccountsBinding
import kotlinx.android.synthetic.main.fragment_accounts.*
import org.koin.androidx.scope.currentScope

class AccountsFragment: Fragment() {

    private val viewModel: AccountsViewModel by lazy { requireActivity().currentScope.get<AccountsViewModel>() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeAccounts()
        observeState()
    }

    private fun observeState() {
        viewModel.state.observe(this, Observer {
            startActivity(NewAccountActivity.getIntent(requireContext()))
        })
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val itemBinding: FragmentAccountsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_accounts, container, false)
        itemBinding.viewModel = viewModel
        itemBinding.lifecycleOwner = this
        return itemBinding.root
    }

    private fun observeAccounts() {
        viewModel.accounts.observe(this, Observer { accounts ->
            accounts_list.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = AccountsAdapter(accounts, viewModel)
            }
        })
    }
}