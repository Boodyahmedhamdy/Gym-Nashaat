package com.example.gymnashaat.data.db

import android.content.Context
import androidx.room.Room

object DatabaseProvider {
    private val db: NashaatGymDB? = null
    fun provideDatabase(context: Context): NashaatGymDB {
        if(db == null) {
            return Room.databaseBuilder(
                context, NashaatGymDB::class.java, "nashaat_db"
            ).build()
        }
        return db
    }
}