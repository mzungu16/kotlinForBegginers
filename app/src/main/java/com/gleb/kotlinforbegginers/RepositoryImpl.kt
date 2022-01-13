package com.gleb.kotlinforbegginers

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


class RepositoryImpl : Repository {
    private val liveDataToObserveRepo: MutableLiveData<List<FilmCardDTO?>> = MutableLiveData()
    private val liveDataToObserveRepo2: MutableLiveData<List<CreditsCardDTO?>> = MutableLiveData()

    override fun getFilmCardsFromServer(): LiveData<List<FilmCardDTO?>> {
        return liveDataToObserveRepo
    }

    override fun getFilmInternetAccess() {
        InternetLoader.loadFilmWithRetrofit(object : InternetLoader.Listener<List<FilmCardDTO?>> {
            override fun on(arg: List<FilmCardDTO?>) {
                liveDataToObserveRepo.value = arg
            }
        })
    }

    override fun getCreditCardsFromServer(): LiveData<List<CreditsCardDTO?>> {
        return liveDataToObserveRepo2
    }

    override fun getCreditInternetAccess(profileId: Int?) {
        InternetLoader.loadCreditsWithRetrofit(profileId,
            object : InternetLoader.Listener<List<CreditsCardDTO?>> {
                override fun on(arg: List<CreditsCardDTO?>) {
                    liveDataToObserveRepo2.value = arg
                }
            })
    }


}