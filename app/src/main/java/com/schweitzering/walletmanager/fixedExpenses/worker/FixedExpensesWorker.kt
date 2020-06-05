package com.schweitzering.walletmanager.fixedExpenses.worker

import android.content.Context
import android.os.Bundle
import androidx.work.PeriodicWorkRequest
import androidx.work.RxWorker
import androidx.work.WorkerParameters
import com.google.firebase.analytics.FirebaseAnalytics
import io.reactivex.Observable
import io.reactivex.Single
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.util.*
import java.util.concurrent.TimeUnit

/*
    Execute worker once a day roughly at 00.00 AM to check if there's a FixedExpense to create
    that have entered a new period
 */
class FixedExpensesWorker(appContext: Context,
                          workerParams: WorkerParameters) :
    RxWorker(appContext, workerParams), KoinComponent {

    companion object {
        const val WORKER_ID = "fixed_expense_worker"
        fun getWorker(): PeriodicWorkRequest {
            val currentDate = Calendar.getInstance()
            val runDate = Calendar.getInstance()
            runDate.set(Calendar.HOUR_OF_DAY, 0)
            runDate.set(Calendar.MINUTE, 0)
            runDate.set(Calendar.SECOND, 0)
            runDate.add(Calendar.HOUR_OF_DAY, 24)

            val timeDiff = runDate.timeInMillis - currentDate.timeInMillis
            return PeriodicWorkRequest.Builder(
                FixedExpensesWorker::class.java, 24, TimeUnit.HOURS)
                .setInitialDelay(timeDiff, TimeUnit.MILLISECONDS)
                .build()
        }
    }

    private val viewModel: FixedExpensesWorkerViewModel by inject()

    override fun createWork(): Single<Result> {
        val bundle = Bundle()
        return Observable.range(0,1)
            .flatMapSingle { viewModel.createFixedExpensesForPeriod() }
            .toList()
            .map {
                bundle.putLong(FirebaseAnalytics.Param.START_DATE, System.currentTimeMillis())
                FirebaseAnalytics.getInstance(applicationContext).logEvent("correct_generator", bundle)
                Result.success()
            }
            .onErrorReturn {
                FirebaseAnalytics.getInstance(applicationContext).logEvent("incorrect_generator", bundle)
                Result.failure()
            }
    }
}