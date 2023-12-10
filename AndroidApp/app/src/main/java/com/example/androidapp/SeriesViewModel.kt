package com.example.androidapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidapp.data.Series
import com.example.androidapp.data.SeriesDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SeriesViewModel(private val seriesDao: SeriesDao) : ViewModel() {
    val dataList: LiveData<List<Series>> = seriesDao.getItems().asLiveData()

    fun addNewItem(seriesTitle: String) {
        val newItem = getNewItemEntry(seriesTitle)
        insertItem(newItem)
    }

    private fun insertItem(series: Series) {
        viewModelScope.launch {
            seriesDao.upsertSeries(series)
        }
    }

    private fun getNewItemEntry(seriesTitle: String): Series {
        return Series(
            title = seriesTitle,
        )
    }

    /*init{
        var titleList = arrayOf(
            "The Sopranos", "Breaking Bad", "The Wire", "Twin Peaks", "The Leftovers", "Game of Thrones", "Better Call Saul", "The Office", "Friends",
            "The Sopranos", "Breaking Bad", "The Wire", "Twin Peaks", "The Leftovers", "Game of Thrones", "Better Call Saul", "The Office", "Friends")

        dataList = arrayListOf<Series>()
        for (i in titleList.indices){
            val series = Series(title = titleList[i])
            dataList.add(series)
        }
    }*/
}

class SeriesViewModelFactory(private val seriesDao: SeriesDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SeriesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SeriesViewModel(seriesDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}