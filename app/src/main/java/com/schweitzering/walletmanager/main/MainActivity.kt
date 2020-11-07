package com.schweitzering.walletmanager.main

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.WorkManager
import com.google.android.material.tabs.TabLayoutMediator
import com.schweitzering.walletmanager.R
import com.schweitzering.walletmanager.fixedExpenses.worker.FixedExpensesWorker
import com.schweitzering.walletmanager.fixedExpenses.worker.FixedExpensesWorker.Companion.WORKER_ID
import com.schweitzering.walletmanager.settings.SettingsActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_toolbar.view.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val CURRENT_POSITION = "current_position"
    }

    private var currentViewPagerPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        periodicWorkerSetup()
    }

    private fun periodicWorkerSetup() {
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(WORKER_ID,
                ExistingPeriodicWorkPolicy.KEEP,
                FixedExpensesWorker.getWorker())
    }

    override fun onResume() {
        super.onResume()
        setViewPager()
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        outState?.putInt(CURRENT_POSITION, currentViewPagerPosition)
        super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?,
                                        persistentState: PersistableBundle?) {
        currentViewPagerPosition = savedInstanceState?.get(CURRENT_POSITION) as Int
        view_pager.currentItem = currentViewPagerPosition
        super.onRestoreInstanceState(savedInstanceState, persistentState)
    }

    private fun setViewPager() {
        val adapter = PagesAdapter(this)
        view_pager.adapter = adapter
        view_pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                currentViewPagerPosition = position
            }
        })
        TabLayoutMediator(tab_layout, view_pager) { tab, position ->
            tab.text = adapter.getTitle(position)
        }.attach()
    }
}
