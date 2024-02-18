package com.example.gymnashaat.mappers

import com.example.gymnashaat.data.entities.PlayerEntity
import com.example.gymnashaat.ui.states.PlayerUiState

fun PlayerEntity.toUiState(): PlayerUiState {
    return PlayerUiState(
        id = id,
        name = name,
        phoneNumber = phoneNumber,
        imageId = imageId
    )
}

fun PlayerUiState.toEntity(): PlayerEntity {
    return PlayerEntity(
        id = id,
        name = name,
        phoneNumber = phoneNumber,
        imageId = imageId
    )
}