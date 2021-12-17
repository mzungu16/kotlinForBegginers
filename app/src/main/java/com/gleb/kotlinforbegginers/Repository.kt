package com.gleb.kotlinforbegginers

interface Repository {
    fun getFilmCardsFromLocalStorage():List<FilmCard>
}