package com.gleb.kotlinforbegginers.model

import androidx.room.*

@Dao
interface FilmDao {
    @Query("SELECT * FROM filmTable")
    fun getAllFilms(): List<FilmEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllFilms(entity: FilmEntity)

    @Update
    fun updateAllFilms(entity: FilmEntity)
}