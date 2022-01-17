package com.gleb.kotlinforbegginers.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gleb.kotlinforbegginers.view.App


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
                insertAllFilmsInDB(App.getMyDatabase(),liveDataToObserveFilms.value as List<FilmCardDTO?>)
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

    override fun getAllFilmsFromDB(dao: FilmDao): List<FilmCardDTO?> {
        return dao.getAllFilms()
            .map {
                FilmCardDTO(
                    id = it.id,
                    original_title = it.originalTitle,
                    overview = it.overview,
                    poster_path = it.posterPath,
                    vote_average = it.voteAverage,
                )
            }
    }

    override fun insertAllFilmsInDB(dao: FilmDao, filmCards: List<FilmCardDTO?>) {
        for (filmCard in filmCards) {
            dao.insertAllFilms(
                FilmEntity(
                    id = filmCard?.id,
                    originalTitle = filmCard?.original_title,
                    overview = filmCard?.overview,
                    posterPath = filmCard?.poster_path,
                    voteAverage = filmCard?.vote_average
                )
            )
        }
    }
}