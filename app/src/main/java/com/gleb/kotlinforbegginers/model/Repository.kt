package com.gleb.kotlinforbegginers.model

import androidx.lifecycle.LiveData


interface Repository {
    //films section
    fun getFilmCardsFromServer(): LiveData<List<FilmCardDTO?>>
    fun getFilmInternetAccess()

    //actors section
    fun getCreditCardsFromServer(): LiveData<List<CreditsCardDTO?>>
    fun getCreditInternetAccess(profileId: Int?)

    //genre section
    fun getGenreCardsFromServer(): LiveData<List<GenreCardDTO?>>
    fun getGenreInternetAccess()

    //filmByGenre section
    fun getFilmByGenreFromServer(): LiveData<List<FilmByGenreCardDTO?>>
    fun getFilmByGenreInternetAccess(genreId: Int?)

    //database section
    fun getAllFilmsFromDB(dao: FilmDao): List<FilmCardDTO?>
    fun insertAllFilmsInDB(dao: FilmDao, filmCards: List<FilmCardDTO?>)
}
