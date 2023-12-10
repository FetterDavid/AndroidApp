package com.example.androidapp.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.androidapp.data.Series
import com.example.androidapp.data.SeriesDao
import kotlinx.coroutines.flow.Flow

class SeriesRepository(private val seriesDao: SeriesDao) {

    val allSeries: Flow<List<Series>> = seriesDao.getItems()

    fun getSeries(id: Int): Flow<Series>{
        return seriesDao.getItem(id)
    }

    @WorkerThread
    suspend fun upsert(series: Series) {
        seriesDao.upsertSeries(series)
    }

    @WorkerThread
    suspend fun delete(series: Series) {
        seriesDao.deleteSeries(series)
    }

}