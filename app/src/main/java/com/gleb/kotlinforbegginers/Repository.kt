package com.gleb.kotlinforbegginers

interface Repository {
    fun getFilmCardsFromServer():List<FactDTO?>
}