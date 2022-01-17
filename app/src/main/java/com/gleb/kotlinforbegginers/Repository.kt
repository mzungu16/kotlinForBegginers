package com.gleb.kotlinforbegginers

import androidx.lifecycle.LiveData


interface Repository {
    fun getFilmCardsFromServer(): LiveData<List<FilmCardDTO?>>
    fun getFilmInternetAccess()

    fun getCreditCardsFromServer(): LiveData<List<CreditsCardDTO?>>
    fun getCreditInternetAccess(profileId: Int?)
}
