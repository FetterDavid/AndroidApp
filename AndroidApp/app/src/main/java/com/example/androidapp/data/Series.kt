package com.example.androidapp.data

import android.content.IntentSender.OnFinished
import android.icu.text.CaseMap.Title
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Series(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val numberOfSeasons : Int,
    val numberOfEpisodesPerSeason: Int,
    val currentSeasons : Int,
    val currentEpisode: Int,
    val finished: Boolean
)
{
    fun getCurrentSeasonEpisodeFormat(): String {
        return "S${currentSeasons.toString().padStart(2, '0')}E${currentEpisode.toString().padStart(2, '0')}"
    }
}
