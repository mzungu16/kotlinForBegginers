package com.gleb.kotlinforbegginers

data class FilmCard(
    val image: Int,
    val imageOfMainActor: Int,
    val imageOfMainActor2: Int,
    val imageOfMainActor3: Int,
    val title: String,
    val description: String
)

fun getFilmCards() = listOf(
    FilmCard(
        R.drawable.starwars,
        R.drawable.actor1,
        R.drawable.actor2,
        R.drawable.actor3,
        "Tracers",
        "Lorem Ipsum is simply dummy text of the printing " +
                "and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type " +
                "and scrambled it to make a type specimen book."
    ),
    FilmCard(
        R.drawable.lordoftherings,
        R.drawable.actor4,
        R.drawable.actor5,
        R.drawable.actor6,
        "Star Wars",
        "It is a long established fact that a reader will " +
                "be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution" +
                " of letters, as opposed to using. Content here, content here', making it look like readable English."
    )
)