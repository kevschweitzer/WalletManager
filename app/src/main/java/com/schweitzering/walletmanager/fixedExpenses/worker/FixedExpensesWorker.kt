package com.schweitzering.walletmanager.fixedExpenses.worker

import android.content.Context
import androidx.work.*
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.util.*
import java.util.concurrent.TimeUnit

/*
    Execute worker once a day roughly at 00.00 AM to check if there's a FixedExpense to create
    that have entered a new period
 */

class FixedExpensesWorker(appContext: Context,
                          workerParams: WorkerParameters): Worker(appContext, workerParams), KoinComponent {

    companion object {
        const val WORKER_ID = "fixed_exp_worker"
        fun getWorker(): OneTimeWorkRequest {
            val currentDate = Calendar.getInstance()
            val dueDate = Calendar.getInstance()
            // Set Execution around 01:00:00 AM
            dueDate.set(Calendar.HOUR_OF_DAY, 1)
            dueDate.set(Calendar.MINUTE, 0)
            dueDate.set(Calendar.SECOND, 0)
            if (dueDate.before(currentDate)) {
                dueDate.add(Calendar.HOUR_OF_DAY, 24)
            }
            val timeDiff = dueDate.timeInMillis - currentDate.timeInMillis
            return OneTimeWorkRequestBuilder<FixedExpensesWorker>()
                .setInitialDelay(timeDiff, TimeUnit.MILLISECONDS)
                .build()
        }
    }

    private val viewModel: FixedExpensesWorkerViewModel by inject()
    private val disposables = CompositeDisposable()

    //TODO: Add retry policy
    override fun doWork(): Result {
         val disposable = viewModel.createFixedExpensesForPeriod()
             .subscribeOn(Schedulers.io())
             .subscribe()
        disposables.add(disposable)

        //create again for next day
        WorkManager.getInstance(applicationContext).enqueueUniqueWork(WORKER_ID, ExistingWorkPolicy.KEEP, getWorker())

        return Result.success()
    }

    override fun onStopped() {
        super.onStopped()
        disposables.clear()
    }
}