package com.example.boardgamestools.model.listViewComponents

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.boardgamestools.R

class PlayerListViewHolder(itemView : View, private val recyclerClickInterface: RecyclerClickInterface) : RecyclerView.ViewHolder(itemView) {

    private val playerName : TextView = itemView.findViewById(R.id.tv_playerName)
    private val playerScore : TextView = itemView.findViewById(R.id.tv_playerScore)

    fun bind(name:String? , score : Int?){
        playerName.text = name
        playerScore.text = score.toString()

        itemView.setOnClickListener {
            val confirmationScreenBuilder = AlertDialog.Builder(it.context)
            //val confirmationScreenView = LayoutInflater.inflate(R.id.asfasf) para hacer con vista personalizada
            //confirmationScreenBuilder.setView(confirmationScreenView)
            confirmationScreenBuilder.setTitle("Modify?")
            confirmationScreenBuilder.setMessage("Are you sure to modify the player: $name")
            confirmationScreenBuilder.setPositiveButton("Yes", DialogInterface.OnClickListener { _, _ ->
                // creo saber que es lo que estoy haciendo
                if (adapterPosition !=ListView.INVALID_POSITION) recyclerClickInterface.onClick(adapterPosition)
            })
            confirmationScreenBuilder.setNegativeButton("No", DialogInterface.OnClickListener { _, _ ->  })
            confirmationScreenBuilder.create().show()
        }
    }

    companion object{
        fun create(parent: ViewGroup, recyclerClickInterface: RecyclerClickInterface) : PlayerListViewHolder{
            val view : View = LayoutInflater.from(parent.context)
                .inflate(R.layout.player_list_item,parent,false)
            return PlayerListViewHolder(view, recyclerClickInterface )
        }
    }
}