package com.example.boardgamestools.model.gameListComponents

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.boardgamestools.R
import com.example.boardgamestools.model.utilities.Game
import com.example.boardgamestools.model.utilities.ListClickInterface

class GamesViewHolder(itemView: View, private val listClickInterface: ListClickInterface) : RecyclerView.ViewHolder(itemView) {

    private val gameNameView: TextView = itemView.findViewById(R.id.tv_gameName)
    private val gameIco : ImageView = itemView.findViewById(R.id.iv_gameLogo)

    fun bind(game: Game) {
        gameNameView.text = game.name
        gameIco.setImageDrawable(ResourcesCompat.getDrawable(itemView.resources,game.icon,null))

        itemView.setOnClickListener {
            listClickInterface.onClick(adapterPosition)
        }
    }

    companion object {
        fun create(parent: ViewGroup, listClickInterface: ListClickInterface): GamesViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.game_list_item, parent, false)
            return GamesViewHolder(view, listClickInterface)
        }
    }
}