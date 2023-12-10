package com.example.androidapp

import android.app.Application
import com.example.androidapp.data.SeriesRoomDatabase

class SeriesApplication: Application() {
    val database: SeriesRoomDatabase by lazy { SeriesRoomDatabase.getDatabase(this) }
}