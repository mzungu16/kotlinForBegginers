package com.gleb.kotlinforbegginers

data class FilmCardDTO(
    val results: List<FactDTO?>
)

data class FactDTO(
    val id: Int?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val vote_average: Double?,
//    "release_date" : "2021-12-17",
)