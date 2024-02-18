package com.example.gymnashaat.ui.states

data class AllPlayersScreenUiState(
    val players: List<PlayerUiState> = emptyList(),
    val isLoading: Boolean = true,
    val error: String = "",
    val showBottomSheet: Boolean = false
)
