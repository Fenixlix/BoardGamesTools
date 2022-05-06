package com.example.boardgamestools.viewmodel

import androidx.lifecycle.*
import com.example.boardgamestools.model.roomData.PlayerEntity
import com.example.boardgamestools.model.repository.PlayerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val repository: PlayerRepository
    ) : ViewModel() {
    val allPlayers : LiveData<List<PlayerEntity>> = repository.allPlayers.asLiveData()

    var round = 0
    var turn = 1

    fun getPlayer(id : Int) : PlayerEntity? {
        return allPlayers.value?.get(id)
    }

    fun insert(player : PlayerEntity) = viewModelScope.launch {
        repository.insertNewPlayer(player)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }

    fun updateScore(player: PlayerEntity) = viewModelScope.launch {
        repository.updatePlayer(player)
    }

    fun resetScore(players : List<PlayerEntity>) = viewModelScope.launch {
        repository.resetScores(players)
    }

    fun deletePlayer(player: PlayerEntity) = viewModelScope.launch {
        repository.deletePlayer(player)
    }
}