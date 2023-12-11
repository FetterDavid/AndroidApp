package com.example.androidapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.androidapp.data.CatFact
import com.example.androidapp.data.Series
import com.example.androidapp.repository.SeriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Response

class SeriesViewModel(private val repository: SeriesRepository) : ViewModel() {
    val dataList: LiveData<List<Series>> = repository.allSeries.asLiveData()
    val fact: MutableLiveData<Response<CatFact>> = MutableLiveData()

    val transformedFact: LiveData<String> = fact.map { response ->
        if (response.isSuccessful && response.body() != null) {
            "„${response.body()!!.fact}”"
        } else {
            ""
        }
    }

    fun getItem(id: Int):Flow<Series> {
        return repository.getSeries(id)
    }

    fun getCatFact(){
        viewModelScope.launch {
            val response = repository.getCatFact()
            fact.value =response
        }
    }

    fun addNewItem(seriesTitle: String, numberOfSeasons: Int, numberOfEpisodesPerSeason: Int) {
        val newItem = getNewItemEntry(seriesTitle,numberOfSeasons,numberOfEpisodesPerSeason)
        upsertItem(newItem)
    }

    fun deleteItem(series: Series) {
        viewModelScope.launch {
            repository.delete(series)
        }
    }

    private fun upsertItem(series: Series) {
        viewModelScope.launch {
            repository.upsert(series)
        }
    }

    private fun getNewItemEntry(seriesTitle: String, numberOfSeasons: Int, numberOfEpisodesPerSeason: Int): Series {
        return Series(
            title = seriesTitle,
            numberOfSeasons =  numberOfSeasons,
            numberOfEpisodesPerSeason = numberOfEpisodesPerSeason,
            currentSeasons = 1,
            currentEpisode = 1
        )
    }

    fun watchNextEpisonde(series: Series) {
        var updatedSeries:Series
        if(series.numberOfSeasons==series.currentSeasons && series.numberOfEpisodesPerSeason==series.currentEpisode) return
        if(series.numberOfEpisodesPerSeason==series.currentEpisode)
        {
            updatedSeries = series.copy(currentSeasons = series.currentSeasons + 1, currentEpisode = 1)
        }
        else updatedSeries = series.copy(currentEpisode = series.currentEpisode + 1)
        upsertItem(updatedSeries)
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