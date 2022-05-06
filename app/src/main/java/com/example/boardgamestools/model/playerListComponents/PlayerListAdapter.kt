package com.example.boardgamestools.model.playerListComponents

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.boardgamestools.model.roomData.PlayerEntity
import com.example.boardgamestools.model.utilities.ListClickInterface

class PlayerListAdapter(listClickInterface: ListClickInterface) : ListAdapter<PlayerEntity,PlayerListViewHolder>(PlayersComparator()){

    private val recyclerClickI = listClickInterface

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerListViewHolder {
        return PlayerListViewHolder.create(parent, recyclerClickI)
    }

    override fun onBindViewHolder(holder: PlayerListViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.name,current.score)
    }

    class PlayersComparator : DiffUtil.ItemCallback<PlayerEntity>() {
        override fun areItemsTheSame(oldItem: PlayerEntity, newItem: PlayerEntity): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: PlayerEntity, newItem: PlayerEntity): Boolean {
            return (oldItem.name == newItem.name && oldItem.score == newItem.score)
        }
    }

}