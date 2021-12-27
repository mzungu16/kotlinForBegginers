package com.gleb.kotlinforbegginers

import android.util.Log


class RepositoryImpl : Repository {

    override fun getFilmCardsFromServer(): List<FactDTO?> {
//        Log.d(FilmLoader.TAG, "In RepositoryImpl ${FilmLoader.load()}")
        return FilmLoader.load()
    }
}