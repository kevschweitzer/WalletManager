package com.schweitzering.data.inject

import androidx.room.Room
import androidx.room.RoomDatabase
import com.schweitzering.data.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    single<RoomDatabase> {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java, "wallet-database"
        ).build()
    }
}