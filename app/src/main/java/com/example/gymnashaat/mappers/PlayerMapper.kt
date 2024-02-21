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

fun PlayerUiState.toFirestoreObject(): HashMap<String, Any?> {
    return hashMapOf(
        "id" to id,
        "name" to name,
        "phone" to phoneNumber,
        "image_id" to imageId
    )
}