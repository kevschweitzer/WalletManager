package com.schweitzering.walletmanager.main

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.google.android.material.tabs.TabLayoutMediator
import com.schweitzering.walletmanager.R
import com.schweitzering.walletmanager.fixedExpenses.worker.FixedExpensesWorker
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private var currentViewPagerPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        periodicWorkerSetup()
    }

    private fun periodicWorkerSetup() {
        val worker = FixedExpensesWorker.getWorker()
        WorkManager.getInstance(this).enqueue(worker)
    }

    override fun onResume() {
        super.onResume()
        setViewPager()
    }

    override fun onStop() {
        super.onStop()
        Log.e("OnStop","called")
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        outState?.putInt("current", currentViewPagerPosition)
        super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onRestoreInstanceState(
        savedInstanceState: Bundle?,
        persistentState: PersistableBundle?
    ) {
        currentViewPagerPosition = savedInstanceState?.get("current") as Int
        view_pager.currentItem = currentViewPagerPosition
        super.onRestoreInstanceState(savedInstanceState, persistentState)
    }



    private fun setViewPager() {
        val adapter = PagesAdapter(this)
        view_pager.adapter = adapter
        view_pager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                currentViewPagerPosition = position
            }
        })
        TabLayoutMediator(tab_layout, view_pager) { tab, position ->
            tab.text = adapter.getTitle(position)
        }.attach()
    }

}
