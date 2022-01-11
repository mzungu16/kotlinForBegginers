package com.gleb.kotlinforbegginers

data class GenresDTO(
    val genre: List<GenreCardDTO?>
)

data class GenreCardDTO(
    val id: Int?,
    val name: String?,
)