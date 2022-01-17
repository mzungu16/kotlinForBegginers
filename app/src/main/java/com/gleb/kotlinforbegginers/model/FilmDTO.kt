package com.gleb.kotlinforbegginers.model

data class FilmDTO(
    val results: List<FilmCardDTO?>
)

data class FilmCardDTO(
    val id: Int?,
    val original_title: String?,
    val overview: String?,
    val poster_path: String?,
    val vote_average: Double?,
//    "release_date" : "2021-12-17",
)