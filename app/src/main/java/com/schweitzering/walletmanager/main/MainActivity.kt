package com.schweitzering.walletmanager.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.schweitzering.walletmanager.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        setViewPager()

    }
    private fun setViewPager() {
        val adapter = PagesAdapter(this)
        view_pager.adapter = adapter
        TabLayoutMediator(tab_layout, view_pager) { tab, position ->
            tab.text = adapter.getTitle(position)
        }.attach()
    }

}
