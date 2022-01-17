package com.gleb.kotlinforbegginers.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "filmTable")
data class FilmEntity(
    @PrimaryKey() val id: Int?,
    val originalTitle: String?,
    val overview: String?,
    val posterPath: String?,
    val voteAverage: Double?,
)