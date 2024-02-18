package com.example.gymnashaat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.gymnashaat.data.entities.PlayerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDao {

    @Insert
    suspend fun insertPlayer(playerEntity: PlayerEntity)

    @Query("DELETE FROM players")
    suspend fun deleteAllPlayers()

    @Query("SELECT * FROM players")
    fun getAllPlayers(): Flow<List<PlayerEntity>>

}