package com.example.boardgamestools.viewmodel

import androidx.lifecycle.*
import com.example.boardgamestools.model.repository.GameRepository
import com.example.boardgamestools.model.roomData.PlayerEntity
import com.example.boardgamestools.model.repository.PlayerRepository
import com.example.boardgamestools.model.roomData.GameDataEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val playerRepo : PlayerRepository,
    private val gameRepo : GameRepository
    ) : ViewModel() {

    // Get the list of players from the db
    private val _allPlayers : LiveData<List<PlayerEntity>> = playerRepo.allPlayers.asLiveData()

    val allPlayers = _allPlayers

    fun getPlayer(id : Int) : PlayerEntity? {
        return allPlayers.value?.get(id)
    }

    fun insert(player : PlayerEntity) = viewModelScope.launch {
        playerRepo.insertNewPlayer(player)
    }

    fun deleteAll() = viewModelScope.launch {
        playerRepo.deleteAll()
    }

    fun updateScore(player: PlayerEntity) = viewModelScope.launch {
        playerRepo.updatePlayer(player)
    }

    fun resetScore(players : List<PlayerEntity>) = viewModelScope.launch {
        playerRepo.resetScores(players)
    }

    fun deletePlayer(player: PlayerEntity) = viewModelScope.launch {
        playerRepo.deletePlayer(player)
    }

    // ----- Game related functions ----- //
    fun saveGame(gameData : GameDataEntity) = viewModelScope.launch {
        gameRepo.saveGame(gameData)
    }
    suspend fun loadGame(name : String) : GameDataEntity? {
        var gameData : GameDataEntity? = null
        viewModelScope.launch {
            val data = viewModelScope.async{
                gameRepo.loadGame(name)
            }
            gameData = data.await()
        }.join()
        return gameData
    }
}