package com.example.androidapp

import com.example.androidapp.data.Series

class SeriesViewModel {
    lateinit var dataList: ArrayList<Series>

    constructor(){
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