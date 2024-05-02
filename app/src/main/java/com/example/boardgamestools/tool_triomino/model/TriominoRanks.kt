package com.example.boardgamestools.tool_triomino.model

import androidx.annotation.DrawableRes
import com.example.boardgamestools.R

enum class TriominoRanks(@DrawableRes val positionGraf: Int) {
    FIRS(R.drawable.t_rank_1),
    SECOND(R.drawable.t_rank_2),
    THIRD(R.drawable.t_rank_3),
    GG(R.drawable.t_rank_gg);

    companion object {
        fun getNewRanking(
            ranking: TriominoRanking,
            score: Int
        ): TriominoRanking {
            return when {
                score > ranking.first -> {
                    ranking.copy(
                        first = score,
                        second = ranking.first,
                        third = ranking.second
                    )
                }

                score > ranking.second -> {
                    ranking.copy(
                        second = score,
                        third = ranking.second
                    )
                }

                score > ranking.third -> {
                    ranking.copy(
                        third = score
                    )
                }

                else -> {
                    ranking
                }
            }
        }

        fun getPlace(
            ranking: TriominoRanking,
            score: Int
        ): Int {
            return when {
                score == 0 -> 3
                score >= ranking.first -> 0
                score >= ranking.second -> 1
                score >= ranking.third -> 2
                else -> 3
            }
        }
    }
}