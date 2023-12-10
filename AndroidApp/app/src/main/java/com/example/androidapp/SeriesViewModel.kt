package com.example.androidapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidapp.data.Series

class SeriesViewModel: ViewModel() {
    lateinit var dataList: ArrayList<Series>

    init{
        var titleList = arrayOf(
            "The Sopranos", "Breaking Bad", "The Wire", "Twin Peaks", "The Leftovers", "Game of Thrones", "Better Call Saul", "The Office", "Friends",
            "The Sopranos", "Breaking Bad", "The Wire", "Twin Peaks", "The Leftovers", "Game of Thrones", "Better Call Saul", "The Office", "Friends")

        dataList = arrayListOf<Series>()
        for (i in titleList.indices){
            val series = Series(title = titleList[i])
            dataList.add(series)
        }
    }
}

class SeriesViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SeriesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SeriesViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}