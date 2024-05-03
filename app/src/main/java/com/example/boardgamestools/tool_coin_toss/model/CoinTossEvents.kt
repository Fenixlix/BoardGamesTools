package com.example.boardgamestools.tool_coin_toss.model

sealed interface CoinTossEvents {
    data object TossCoin : CoinTossEvents
    data object AddCounterBox : CoinTossEvents
    data class SelectBox(val position : Int) : CoinTossEvents
}