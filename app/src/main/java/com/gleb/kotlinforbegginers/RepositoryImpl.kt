package com.gleb.kotlinforbegginers

import android.util.Log


class RepositoryImpl : Repository{
    override fun getFilmCardsFromServer() {
        FilmLoader.load(object : FilmLoader.Listener<List<FactDTO?>> {
            override fun on(arg: List<FactDTO?>) {
            }
        })
    }

}