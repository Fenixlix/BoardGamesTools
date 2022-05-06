package com.example.boardgamestools.model.utilities

class Dice (private val typeOfDice : DiceType = DiceType.D6) {

    enum class DiceType (val tossRange : Int){
        D6(6), D8(8), D10(10), D12(12), D20(20)
    }

    fun rollDice(typeOfDice: DiceType = this.typeOfDice): Int{
        return (1 .. typeOfDice.tossRange).random()
    }

}
