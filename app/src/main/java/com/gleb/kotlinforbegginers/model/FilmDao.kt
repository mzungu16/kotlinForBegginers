package com.gleb.kotlinforbegginers.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FilmDao {
    @Query("SELECT * FROM filmTable")
    fun getAllFilms(): List<FilmEntity>

    @Insert
    fun insertAllFilms(entity: FilmEntity)
}