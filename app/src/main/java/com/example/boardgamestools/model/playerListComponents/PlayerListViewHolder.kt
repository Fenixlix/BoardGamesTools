package com.example.boardgamestools.model.playerListComponents

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.boardgamestools.R
import com.example.boardgamestools.model.utilities.ListClickInterface

class PlayerListViewHolder(itemView : View, private val listClickInterface: ListClickInterface) : RecyclerView.ViewHolder(itemView) {

    private val playerName : TextView = itemView.findViewById(R.id.tv_playerName)
    private val playerScore : TextView = itemView.findViewById(R.id.tv_playerScore)

    fun bind(name:String? , score : Int?){
        playerName.text = name
        playerScore.text = score.toString()

        itemView.setOnClickListener {
            val confirmationScreenBuilder = AlertDialog.Builder(it.context)
            confirmationScreenBuilder.setTitle("Modify?")
            confirmationScreenBuilder.setMessage("Are you sure to modify the player: $name")
            confirmationScreenBuilder.setPositiveButton("Yes", DialogInterface.OnClickListener { _, _ ->
                if (adapterPosition !=ListView.INVALID_POSITION) listClickInterface.onClick(adapterPosition)
            })
            confirmationScreenBuilder.setNegativeButton("No", DialogInterface.OnClickListener { _, _ ->  })
            confirmationScreenBuilder.create().show()
        }
    }

    companion object{
        fun create(parent: ViewGroup, listClickInterface: ListClickInterface) : PlayerListViewHolder{
            val view : View = LayoutInflater.from(parent.context)
                .inflate(R.layout.player_list_item,parent,false)
            return PlayerListViewHolder(view, listClickInterface )
        }
    }
}