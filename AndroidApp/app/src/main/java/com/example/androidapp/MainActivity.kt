package com.example.androidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidapp.data.Series

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: ArrayList<Series>
    lateinit var titleList: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        titleList = arrayOf(
            "The Sopranos",
            "Breaking Bad",
            "The Wire",
            "Twin Peaks",
            "The Leftovers",
            "Game of Thrones",
            "Better Call Saul",
            "The Office",
            "Friends",
            "The Sopranos",
            "Breaking Bad",
            "The Wire",
            "Twin Peaks",
            "The Leftovers",
            "Game of Thrones",
            "Better Call Saul",
            "The Office",
            "Friends"
        )

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        dataList = arrayListOf<Series>()
        getData()
    }

    private fun getData(){
        for (i in titleList.indices){
            val series = Series(title = titleList[i])
            dataList.add(series)
        }
        recyclerView.adapter = SeriesAdapter(dataList)
    }
}