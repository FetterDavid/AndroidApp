package com.example.androidapp

import android.app.Application
import com.example.androidapp.data.SeriesRoomDatabase
import com.example.androidapp.repository.SeriesRepository

class SeriesApplication: Application() {
    val database: SeriesRoomDatabase by lazy { SeriesRoomDatabase.getDatabase(this) }
    val repository by lazy { SeriesRepository(database.seriesDao()) }
}