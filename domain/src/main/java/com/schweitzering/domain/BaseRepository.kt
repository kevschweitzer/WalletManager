package com.schweitzering.domain

import androidx.lifecycle.LiveData

interface BaseRepository<T> {
    fun insert(model: T)
    fun delete(model: T): Any
    fun update(model: T)
    fun getAll(): LiveData<List<T>>
}