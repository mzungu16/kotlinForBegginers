package com.gleb.kotlinforbegginers.model

class GenreDTO(
    val genres: List<GenreCardDTO?>
)

data class GenreCardDTO(
    val id: Int?,
    val name: String?
)