package com.example.boardgamestools.tool_coin_toss.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boardgamestools.tool_coin_toss.model.CoinCounter
import com.example.boardgamestools.tool_coin_toss.model.CoinTossEvents
import com.example.boardgamestools.tool_coin_toss.model.CoinTossState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class CoinTossViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(CoinTossState())
    private val counterList = MutableStateFlow(listOf(CoinCounter()))
    private var numOfBoxCounters = 1    // Used to ensure the naming and order easing the code
    private var numOfCoinTossed = 1     // For secure the update due to the boolean repeatability

    val state = combine(_state, counterList) { state, counters ->
        state.copy(
            counterList = counters
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = CoinTossState()
    )

    fun onEvent(event: CoinTossEvents) {
        when (event) {
            CoinTossEvents.AddCounterBox -> {
                addCounterBox()
            }

            is CoinTossEvents.SelectBox -> {
                selectBox(event.position)
            }

            CoinTossEvents.TossCoin -> {
                tossCoin()
            }
        }
    }

    private fun addCounterBox() {
        val newList = mutableListOf<CoinCounter>()
        newList.addAll(counterList.value)
        newList.add(CoinCounter(position = numOfBoxCounters++))
        counterList.update {
            newList
        }
    }

    private fun selectBox(newPosition: Int) {
        _state.update {
            it.copy(
                selectedBox = newPosition
            )
        }
    }

    private fun tossCoin() {
        val headOrTail = Random.nextBoolean()
        if (_state.value.counterList.isNotEmpty()) {
            if (headOrTail) {
                counterList.value[_state.value.selectedBox].numOfHeads += 1
            } else {
                counterList.value[_state.value.selectedBox].numOfTails += 1
            }
        }
        _state.update {
            it.copy(
                headOrTail = headOrTail,
                numOfCoinsTossed = numOfCoinTossed++
            )
        }
    }
}