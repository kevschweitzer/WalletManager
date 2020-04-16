package com.schweitzering.data

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.*
import androidx.room.Room
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.schweitzering.data.database.AppDatabase
import com.schweitzering.data.finance.FinanceDatabaseManager
import com.schweitzering.data.finance.FinanceEntity
import com.schweitzering.domain.finance.FinanceCategory
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.sql.Timestamp
import java.time.LocalDate

@RunWith(AndroidJUnit4ClassRunner::class)
class FinanceDaoTest {

    private lateinit var databaseManager: FinanceDatabaseManager
    private lateinit var appDatabase: AppDatabase
    //private val dao by lazy { database.financeDao() }

    //ID is set by database in order so the entities need to have id set to 1,2,3...n
    private val entity1 = FinanceEntity(
        id = 1,
        value = 200f,
        date = Timestamp(System.currentTimeMillis() - 10 * ONE_DAY_IN_MILLIS),
        category = FinanceCategory.INCOME,
        financeCategoryType = SALARY_TYPE
    )
    private val entity2 = FinanceEntity(
        id = 2,
        value = 10.5f,
        date = Timestamp(System.currentTimeMillis() - 5 * ONE_DAY_IN_MILLIS),
        category = FinanceCategory.EXPENSE,
        financeCategoryType = FOOD_TYPE
    )
    private val entity3 = FinanceEntity(
        id = 3,
        value = 92.3f,
        date = Timestamp(System.currentTimeMillis() - 2 * ONE_DAY_IN_MILLIS),
        category = FinanceCategory.EXPENSE,
        financeCategoryType = CLOTHES_TYPE
    )
    private val entity4 = FinanceEntity(
        id = 4,
        value = 92.3f,
        date = Timestamp(System.currentTimeMillis()),
        category = FinanceCategory.EXPENSE,
        financeCategoryType = CLOTHES_TYPE
    )

    companion object {
        const val SALARY_TYPE = "salary"
        const val FOOD_TYPE = "food"
        const val CLOTHES_TYPE = "clothes"
        const val ONE_DAY_IN_MILLIS = 86400000
    }


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            AppDatabase::class.java).build()
        databaseManager = FinanceDatabaseManager(appDatabase)
    }

    @After
    fun closeDb() {
        appDatabase.close()
    }

    @Test fun insertFinanceEntityTest() {
        databaseManager.insert(entity1)
        databaseManager.getAll().observeOnce {
            assertEquals(it, listOf(entity1))
        }
    }

    @Test fun deleteFinanceEntityTest() {
        databaseManager.insert(entity1)
        databaseManager.delete(entity1)
        databaseManager.getAll().observeOnce {
            assertEquals(it, listOf<FinanceEntity>())
        }
    }

    @Test fun getByCategoryTest() {
        databaseManager.insert(entity1)
        databaseManager.insert(entity2)
        databaseManager.insert(entity3)

        databaseManager.getByCategory(FinanceCategory.EXPENSE).observeOnce {
           assertEquals(it, listOf(entity2, entity3))
        }
    }

    @Test fun getByFinanceCategoryType() {
        databaseManager.insert(entity1)
        databaseManager.insert(entity2)
        databaseManager.insert(entity3)

        databaseManager.getByFinanceCategoryType(SALARY_TYPE).observeOnce {
            assertEquals(it, listOf(entity1))
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
