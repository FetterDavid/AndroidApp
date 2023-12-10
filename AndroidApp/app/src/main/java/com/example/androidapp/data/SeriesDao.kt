package com.example.androidapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface SeriesDao {

    @Upsert
    suspend fun upsertSeries(series: Series)

    @Delete
    suspend fun deleteSeries(series: Series)

    @Query("SELECT * from series ORDER BY title ASC")
    fun getItems(): Flow<List<Series>>
}