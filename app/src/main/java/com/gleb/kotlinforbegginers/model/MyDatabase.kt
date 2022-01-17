package com.gleb.kotlinforbegginers.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FilmEntity::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao
}