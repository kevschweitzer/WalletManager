package com.schweitzering.walletmanager.main

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.schweitzering.walletmanager.balance.BalanceFragment
import com.schweitzering.walletmanager.fixedExpenses.list.FixedExpensesFragment

class PagesAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity) {

    private val pages = listOf(Pair(BalanceFragment(), "Balance"), Pair(FixedExpensesFragment(), "Fixed Expenses"))

    override fun getItemCount(): Int {
        return pages.count()
    }

    override fun createFragment(position: Int): Fragment {
        return pages[position].first
    }

    fun getTitle(position: Int) = pages[position].second
}