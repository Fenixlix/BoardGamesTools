package com.example.boardgamestools.model.recyclerComponents

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.boardgamestools.model.roomData.Game

class GamesListAdapter : ListAdapter<Game, GamesViewHolder>(GamesComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesViewHolder {
        return GamesViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: GamesViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.name)
    }

    class GamesComparator : DiffUtil.ItemCallback<Game>() {
        override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem.name == newItem.name
        }
    }
}