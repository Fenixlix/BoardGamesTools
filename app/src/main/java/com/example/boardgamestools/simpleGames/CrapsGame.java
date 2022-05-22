package com.example.boardgamestools.simpleGames;

import androidx.annotation.NonNull;

import java.util.Random;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * For using the game...
 * First you have to use the takeTurn() method
 * Then check if the game has finish or not with the gameStateMessage() method
 * Finally reset the game with the resetGame() method for start a New Game
 * */
@Singleton
public class CrapsGame {
    @Inject CrapsGame() {}

    // Random object for the toss of the dices.
    private final Random numRandom = new Random();

    // Enum with the states of the game.
    private enum _State {CONTINUE, WIN, LOSE}

    // Initialize the games variables for start the game.
    private _State gameState = _State.CONTINUE;
    private int score = 0;
    private int turnScore = 0;
    private int turn = 1;

    // Make a move in the game loop by tossing the dices
    // Returns the value of the dices
    public int[] takeTurn(){
        int[] dices = tossDice();
        turnScore =  sumDices(dices);

        if (turn++ == 1)
            checkFirstTurn();
        else
            checkConsequentTurns();

        return dices;
    }

    // Reset all the variables to their initial state
    public void resetGame(){
        gameState = _State.CONTINUE;
        score = 0;
        turnScore = 0;
        turn = 1;
    }

    // Return a number with the state of the game
    public int gameState(){
        if(gameState == _State.WIN)
            return 1;
        else if (gameState == _State.CONTINUE)
            return 0;
        else
            return -1;
    }

    // Checks if the player wins of lose in the first turn and get the score point
    private void checkFirstTurn(){
        switch(turnScore){
                // Wins with ether 7 or 11 in the first toss
            case 7:
            case 11:
                gameState = _State.WIN;
                break;
                // Lose with ether 2, 3 or 12 in the first toss
            case 2:
            case 3:
            case 12:
                gameState = _State.LOSE;
                break;
                // The player don't lose or win so, the score is recorded
            default:
                gameState = _State.CONTINUE;
                score = turnScore; // saves the score
                break;
        }
    }

    // Checks the rest of the turns if the player gets to toss the same score
    private void checkConsequentTurns(){
        if(turnScore == score) // Wins getting the same score
            gameState = _State.WIN;
        else
        if(turnScore == 7) // Lose if get 7 before the saved score
            gameState = _State.LOSE;
    }

    // get two random values for the dices of the player
    @NonNull
    private int[] tossDice(){
        int dice1 = 1+numRandom.nextInt(6);
        int dice2 = 1+numRandom.nextInt(6);

        return new int[]{dice1, dice2};
    }

    // add both dices values
    private int sumDices(@NonNull int[] dices){
        return dices[0]+dices[1];
    }
}
