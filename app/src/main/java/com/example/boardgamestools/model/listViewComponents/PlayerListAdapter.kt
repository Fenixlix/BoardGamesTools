package com.example.boardgamestools.model.listViewComponents

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.boardgamestools.model.roomData.PlayerEntity

class PlayerListAdapter(recyclerClickInterface: RecyclerClickInterface) : ListAdapter<PlayerEntity,PlayerListViewHolder>(PlayersComparator()){

    private val recyclerClickI = recyclerClickInterface

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