package com.gleb.kotlinforbegginers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


class RepositoryImpl : Repository {
    private val liveDataToObserveFilms: MutableLiveData<List<FilmCardDTO?>> = MutableLiveData()
    private val liveDataToObserveCredits: MutableLiveData<List<CreditsCardDTO?>> = MutableLiveData()

    override fun getFilmCardsFromServer(): LiveData<List<FilmCardDTO?>> {
        return liveDataToObserveFilms
    }

    override fun getFilmInternetAccess() {
        InternetLoader.loadFilmWithRetrofit(object : InternetLoader.Listener<List<FilmCardDTO?>> {
            override fun on(arg: List<FilmCardDTO?>) {
                liveDataToObserveFilms.value = arg
            }
        })
    }

    override fun getCreditCardsFromServer(): LiveData<List<CreditsCardDTO?>> {
        return liveDataToObserveCredits
    }

    override fun getCreditInternetAccess(profileId: Int?) {
        InternetLoader.loadCreditsWithRetrofit(profileId,
            object : InternetLoader.Listener<List<CreditsCardDTO?>> {
                override fun on(arg: List<CreditsCardDTO?>) {
                    liveDataToObserveCredits.value = arg
                }
            })
    }
}