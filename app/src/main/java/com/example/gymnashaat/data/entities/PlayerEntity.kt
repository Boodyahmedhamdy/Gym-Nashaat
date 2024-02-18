package com.example.gymnashaat.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "players")
data class PlayerEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    val phoneNumber: String = "",
    val imageId: Int = 0
)

