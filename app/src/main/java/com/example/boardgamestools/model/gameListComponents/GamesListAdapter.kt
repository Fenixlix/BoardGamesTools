package com.example.boardgamestools.model.gameListComponents

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.boardgamestools.model.utilities.Game
import com.example.boardgamestools.model.utilities.ListClickInterface

class GamesListAdapter(listClickInterface: ListClickInterface) : ListAdapter<Game, GamesViewHolder>(GamesComparator()) {

    private val recyclerClickI = listClickInterface

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesViewHolder {
        return GamesViewHolder.create(parent, recyclerClickI)
    }

    override fun onBindViewHolder(holder: GamesViewHolder, position: Int) {
        val currentGame = getItem(position)
        holder.bind(currentGame)
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