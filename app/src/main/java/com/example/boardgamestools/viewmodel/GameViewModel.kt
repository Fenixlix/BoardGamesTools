package com.example.boardgamestools.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.boardgamestools.model.repository.GameRepository
import com.example.boardgamestools.model.repository.PlayerRepository
import com.example.boardgamestools.model.roomData.GameDataEntity
import com.example.boardgamestools.model.roomData.PlayerEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val playerRepo: PlayerRepository,
    private val gameRepo: GameRepository
) : ViewModel() {

    // Get the list of players from the db
    val allPlayers = playerRepo.allPlayers.asLiveData()

    fun insert(player: PlayerEntity) = viewModelScope.launch {
        playerRepo.insertNewPlayer(player)
    }

    fun deleteAll() = viewModelScope.launch {
        playerRepo.deleteAll()
    }

    fun updateScore(player: PlayerEntity) = viewModelScope.launch {
        playerRepo.updatePlayer(player)
    }

    fun resetScore(players: List<PlayerEntity>) = viewModelScope.launch {
        playerRepo.resetScores(players)
    }

    fun deletePlayer(player: PlayerEntity) = viewModelScope.launch {
        playerRepo.deletePlayer(player)
    }

    // ----- Game related functions ----- //
    fun saveGame(gameData: GameDataEntity) = viewModelScope.launch {
        gameRepo.saveGame(gameData)
    }

    fun loadGame(name: String) = gameRepo.loadGame(name)


}