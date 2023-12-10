package com.example.androidapp.data

import android.icu.text.CaseMap.Title
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Series(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
)
