package com.gleb.kotlinforbegginers

data class FilmCard(
    val image: Int,
    val title: String
)

fun getFilmCards(): List<FilmCard> {
    return listOf(
        FilmCard(R.drawable.tracers, "Patriot"),
        FilmCard(R.drawable.starwars, "Star Wars")
    )
}