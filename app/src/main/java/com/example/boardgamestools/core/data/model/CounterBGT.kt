package com.example.boardgamestools.core.data.model

data class CounterBGT(
    val owner: String,
    val ownerId: Int,
    val id: Int,
    val name: String = "",
    var points: Int = 0
)

fun CounterBGT.toSaveString() = "${this.name}¡${this.points}|"

fun String.counterSplit(): List<Pair<String, Int>> {
    val result = mutableListOf<Pair<String, Int>>()
    if (this.matches("((\\w|\\s|\\d)*¡(-?\\d){1,8}\\|)+".toRegex())) {
        this.dropLast(1).split("|").forEach {
            val splitString = it.split("¡")
            val newPair = splitString.component1() to splitString.component2().toInt()
            result.add(newPair)
        }
    }
    return result
}