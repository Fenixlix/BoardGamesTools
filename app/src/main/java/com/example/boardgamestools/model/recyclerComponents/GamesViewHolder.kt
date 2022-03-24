package com.example.boardgamestools.model.recyclerComponents

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.boardgamestools.R

class GamesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val gameNameView: TextView = itemView.findViewById(R.id.tv_gameName)

    fun bind(text: String?) {
        gameNameView.text = text
    }

    companion object {
        fun create(parent: ViewGroup): GamesViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.game_list_item, parent, false)
            return GamesViewHolder(view)
        }
    }
}