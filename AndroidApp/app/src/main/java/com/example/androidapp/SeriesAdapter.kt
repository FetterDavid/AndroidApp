package com.example.androidapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.androidapp.data.Series

class SeriesAdapter(private val dataList: ArrayList<Series>,private val onItemClicked: (Series) -> Unit): RecyclerView.Adapter<SeriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.series_item_layout,parent,false)
        return  ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.rvConstraintLayout.setOnClickListener{ onItemClicked(currentItem) }
        holder.rvTitle.text=currentItem.title
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var rvTitle:TextView = itemView.findViewById(R.id.title)
        var rvConstraintLayout: ConstraintLayout = itemView.findViewById(R.id.holder)
    }
}