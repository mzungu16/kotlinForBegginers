package com.gleb.kotlinforbegginers.model

import androidx.lifecycle.LiveData


interface Repository {
    fun getFilmCardsFromServer(): LiveData<List<FilmCardDTO?>>
    fun getFilmInternetAccess()

    fun getCreditCardsFromServer(): LiveData<List<CreditsCardDTO?>>
    fun getCreditInternetAccess(profileId: Int?)

    fun getAllFilmsFromDB(dao: FilmDao):List<FilmCardDTO?>
    fun insertAllFilmsInDB(dao: FilmDao, filmCards: List<FilmCardDTO?>)
}
