package com.arandusgaming.mosaicofhistory.ui

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ImageView
import com.arandusgaming.mosaicofhistory.data.HistoricalFigure

class FigureAdapter(
    private val figures: List<HistoricalFigure>,
    private val onItemClick: (HistoricalFigure) -> Unit
) : RecyclerView.Adapter<FigureAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
        val nameText: TextView = itemView.findViewById(android.R.id.text1)
        val levelText: TextView = itemView.findViewById(android.R.id.text2)
        val figureImage: ImageView = itemView.findViewById(android.R.id.icon)

        init {
            itemView.setOnClickListener {
                onItemClick(figures[bindingAdapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_2, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val figure = figures[position]
        holder.nameText.text = figure.name
        holder.levelText.text = "Level ${figure.level} - ${figure.continent}"
    }

    override fun getItemCount() = figures.size
}
