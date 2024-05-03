package com.example.boardgamestools.tool_coin_toss.model

data class CoinTossState(
    val counterList: List<CoinCounter> = listOf(CoinCounter()),
    val selectedBox: Int = 0,
    val headOrTail: Boolean = false,
    val numOfCoinsTossed: Int = 0
)
