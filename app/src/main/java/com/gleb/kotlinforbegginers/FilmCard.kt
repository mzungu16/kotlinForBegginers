package com.gleb.kotlinforbegginers

data class FilmCard(
    val image: Int,
    val imageOfMainActor: Int,
    val imageOfMainActor2: Int,
    val imageOfMainActor3: Int,
    val title: String,
    val description: String
)

fun getFilmCards(str: String) = listOf(
    FilmCard(
        R.drawable.starwars,
        R.drawable.actor1,
        R.drawable.actor2,
        R.drawable.actor3,
        "Star Wars",
        str.descriptions("Star Wars")
    ),
    FilmCard(
        R.drawable.lordoftherings,
        R.drawable.actor4,
        R.drawable.actor5,
        R.drawable.actor6,
        "Lord of the Rings",
        str.descriptions("Lord of the Rings")
    )
)