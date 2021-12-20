package com.gleb.kotlinforbegginers

class RepositoryImpl : Repository {
    override fun getFilmCardsFromLocalStorage(): List<FilmCard> = getFilmCards()
}