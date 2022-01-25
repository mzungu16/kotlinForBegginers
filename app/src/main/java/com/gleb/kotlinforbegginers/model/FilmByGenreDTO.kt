package com.gleb.kotlinforbegginers.model

class FilmByGenreDTO(
    val results: List<FilmByGenreCardDTO?>
)

data class FilmByGenreCardDTO(
    val original_title: String?,
    val overview: String?,
    val poster_path: String?,
    val vote_average: Double?,
)