package com.example.androidapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidapp.data.Series
import com.example.androidapp.data.SeriesDao
import com.example.androidapp.repository.SeriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SeriesViewModel(private val repository: SeriesRepository) : ViewModel() {
    val dataList: LiveData<List<Series>> = repository.allSeries.asLiveData()

    fun addNewItem(seriesTitle: String, numberOfSeasons: Int, numberOfEpisodesPerSeason: Int) {
        val newItem = getNewItemEntry(seriesTitle,numberOfSeasons,numberOfEpisodesPerSeason)
        insertItem(newItem)
    }

    private fun insertItem(series: Series) {
        viewModelScope.launch {
            repository.upsert(series)
        }
    }

    private fun getNewItemEntry(seriesTitle: String, numberOfSeasons: Int, numberOfEpisodesPerSeason: Int): Series {
        return Series(
            title = seriesTitle,
            numberOfSeasons =  numberOfSeasons,
            numberOfEpisodesPerSeason = numberOfEpisodesPerSeason,
            currentSeasons = 0,
            currentEpisode = 0
        )
    }
}

class SeriesViewModelFactory(private val repository: SeriesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SeriesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SeriesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}