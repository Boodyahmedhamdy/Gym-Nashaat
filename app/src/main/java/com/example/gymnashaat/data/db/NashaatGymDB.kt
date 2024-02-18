package com.example.gymnashaat.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gymnashaat.data.dao.PlayerDao
import com.example.gymnashaat.data.entities.PlayerEntity

@Database(entities = [PlayerEntity::class], version = 1, exportSchema = false)
abstract class NashaatGymDB: RoomDatabase() {

    abstract fun playerDao(): PlayerDao

}