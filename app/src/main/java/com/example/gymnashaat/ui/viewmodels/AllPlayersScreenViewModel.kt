package com.example.gymnashaat.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.gymnashaat.data.dao.PlayerDao
import com.example.gymnashaat.mappers.toEntity
import com.example.gymnashaat.mappers.toUiState
import com.example.gymnashaat.ui.states.AllPlayersScreenUiState
import com.example.gymnashaat.ui.states.PlayerUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AllPlayersScreenViewModel(
    private val playerDao: PlayerDao
): ViewModel() {

    private val _state = MutableStateFlow(AllPlayersScreenUiState())
    val state = _state.asStateFlow()

    init {
        loadPlayersFlow()
    }

    fun loadPlayersFlow() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            playerDao.getAllPlayers().collect { players ->
                withContext(Dispatchers.Main) {
                    _state.update {
                        it.copy(players = players.map { it.toUiState() }, isLoading = false)
                    }
                }
            }
        }
    }

    fun insertPlayer(playerUiState: PlayerUiState) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                playerDao.insertPlayer(playerUiState.toEntity())
            }
        }
    }

    fun deleteAllPlayers() {
        viewModelScope.launch {
            playerDao.deleteAllPlayers()
        }
    }

    fun showBottomSheet() = _state.update { it.copy(showBottomSheet = true) }
    fun hideBottomSheet() = _state.update { it.copy(showBottomSheet = false) }


}

class AllPlayersScreenViewModelFactory(
    private val playerDao: PlayerDao
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AllPlayersScreenViewModel(playerDao = playerDao) as T
    }
}