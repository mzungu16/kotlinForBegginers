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

    fun getFilmReviewFromServer(): LiveData<List<ReviewCardDTO?>>
    fun getFilmReviewInternetAccess(filmId: Int?)
}
