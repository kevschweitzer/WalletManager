package com.schweitzering.data

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.schweitzering.data.Constants.Companion.CLOTHES_TYPE
import com.schweitzering.data.Constants.Companion.FOOD_TYPE
import com.schweitzering.data.Constants.Companion.SALARY_TYPE
import com.schweitzering.data.xsupport.database.AppDatabase
import com.schweitzering.data.transaction.TransactionDatabaseManager
import com.schweitzering.data.transaction.TransactionEntity
import com.schweitzering.domain.transaction.TransactionType
import org.junit.*
import org.junit.Assert.assertEquals
import org.junit.runner.RunWith
import java.sql.Timestamp
import java.time.LocalDate

@RunWith(AndroidJUnit4ClassRunner::class)
@Ignore
class TransactionsDaoTest {

    private lateinit var databaseManager: TransactionDatabaseManager

    private lateinit var appDatabase: AppDatabase

    //ID is set by database in order so the entities need to have id set to 1,2,3...n
    private val entity1 = TransactionEntity(
        categoryId = 0,
        date = Timestamp(System.currentTimeMillis()),
        description = "one description",
        type = TransactionType.INCOME,
        value = 50f
    )
    private val entity2 = TransactionEntity(
        categoryId = 0,
        date = Timestamp(System.currentTimeMillis()),
        description = "two description",
        type = TransactionType.EXPENSE,
        value = 500f
    )
    private val entity3 = TransactionEntity(
        categoryId = 0,
        date = Timestamp(System.currentTimeMillis()),
        description = "three description",
        type = TransactionType.EXPENSE,
        value = 5000f
    )
    private val entity4 = TransactionEntity(
        categoryId = 0,
        date = Timestamp(System.currentTimeMillis()),
        description = "four description",
        type = TransactionType.EXPENSE,
        value = 50000f
    )

    companion object {
        const val ONE_DAY_IN_MILLIS = 86400000
    }


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            AppDatabase::class.java).build()
        databaseManager = TransactionDatabaseManager(appDatabase)
    }

    @After
    fun closeDb() {
        appDatabase.close()
    }

    @Test fun insertTransactionEntityTest() {
        databaseManager.insert(entity1)
        databaseManager.getAll().observeOnce {
            assertEquals(it, listOf(entity1))
        }
    }

    @Test fun deleteTransactionEntityTest() {
        databaseManager.insert(entity1)
        databaseManager.delete(entity1)
        databaseManager.getAll().observeOnce {
            assertEquals(it, listOf<TransactionEntity>())
        }
    }

    @Test fun getBetweenTest() {
        databaseManager.insert(entity1)
        databaseManager.insert(entity2)
        databaseManager.insert(entity3)
        databaseManager.insert(entity4)

        databaseManager.getAll().observeOnce {
            it.forEach{
                Log.e("ALL","${it.id} - ${it.date}")
            }
        }

        //Last 4 days
        databaseManager.getBetween(
            LocalDate.now().minusDays(4) ,
            LocalDate.now()
        ).observeOnce {
            assertEquals(it, listOf(entity3, entity4))
        }
    }
}

//Help classes to test LiveData
class OneTimeObserver<T>(private val handler: (T) -> Unit) : Observer<T>, LifecycleOwner {
    private val lifecycle = LifecycleRegistry(this)

    init {
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
    }

    override fun getLifecycle(): Lifecycle = lifecycle

    override fun onChanged(t: T) {
        handler(t)
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    }
}

fun <T> LiveData<T>.observeOnce(onChangeHandler: (T) -> Unit) {
    val observer = OneTimeObserver(handler = onChangeHandler)
    observe(observer, observer)
}