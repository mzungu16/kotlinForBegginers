package com.gleb.kotlinforbegginers

sealed class State {
    data class Success(val filmCards:List<FilmCardDTO?>) : State()
    data class SuccessToCredits(val creditCard:List<CreditsCardDTO?>) : State()
    data class SuccessToGenres(val genreCard:List<GenreCardDTO?>) : State()
}

