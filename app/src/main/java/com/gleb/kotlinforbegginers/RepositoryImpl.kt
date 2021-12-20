package com.gleb.kotlinforbegginers


class RepositoryImpl : Repository {
    override fun getFilmCardsFromLocalStorage(): List<FilmCard> {
        val str = String()
        return getFilmCards(str)
    }
}