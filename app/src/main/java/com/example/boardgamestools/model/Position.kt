package com.example.boardgamestools.model

import com.example.boardgamestools.R

enum class Position(val positionGraf: Int) {
    FIRS(R.drawable.triomino_first),SECOND(R.drawable.triomino_second),THIRD(R.drawable.triomino_third),GG(R.drawable.triomino_default);

    companion object{
        fun getPosition(firstPlace : Int,secondPlace : Int,thirdPlace : Int, score : Int) : Position{
            return when {
                score>= firstPlace -> {
                    FIRS
                }
                score>= secondPlace -> {
                    SECOND
                }
                score>= thirdPlace -> {
                    THIRD
                }
                else -> GG
            }
        }
    }
}