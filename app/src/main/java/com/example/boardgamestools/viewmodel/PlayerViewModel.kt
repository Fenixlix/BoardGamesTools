package com.example.boardgamestools.viewmodel

import androidx.lifecycle.*
import com.example.boardgamestools.model.roomData.PlayerEntity
import com.example.boardgamestools.model.roomData.PlayerRepository
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class PlayerViewModel(private val repository: PlayerRepository) : ViewModel() {
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

class PlayerViewModelFactory(private val repository: PlayerRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlayerViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PlayerViewModel(repository) as T
        }
        throw IllegalArgumentException("Wrong ViewModel class")
    }

}