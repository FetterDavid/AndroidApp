package com.example.androidapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidapp.data.Series
import com.example.androidapp.databinding.SeriesItemLayoutBinding

class SeriesAdapter(private val onItemClicked: (Series) -> Unit):
    ListAdapter<Series, SeriesAdapter.ViewHolder>(DiffCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            SeriesItemLayoutBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.itemView.setOnClickListener{ onItemClicked(currentItem) }
        holder.bind(currentItem)
    }

    class ViewHolder(private var binding: SeriesItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(series: Series)
        {
            binding.title.text=series.title
            binding.season.text=series.numberOfSeasons.toString()
            binding.episode.text=series.numberOfEpisodesPerSeason.toString()
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Series>() {
            override fun areItemsTheSame(oldItem: Series, newItem: Series): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Series, newItem: Series): Boolean {
                return oldItem.title == newItem.title
            }
        }
    }
}