package com.gleb.kotlinforbegginers.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


class RepositoryImpl : Repository {
    private val liveDataToObserveFilms: MutableLiveData<List<FilmCardDTO?>> = MutableLiveData()
    private val liveDataToObserveCredits: MutableLiveData<List<CreditsCardDTO?>> = MutableLiveData()
    private val liveDataToObserveGenres: MutableLiveData<List<GenreCardDTO?>> = MutableLiveData()
    private val liveDataToObserveFilmByGenre: MutableLiveData<List<FilmByGenreCardDTO?>> =
        MutableLiveData()
    private val liveDataToObserveReview: MutableLiveData<List<ReviewCardDTO?>> = MutableLiveData()

    //film section
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

    //actors section
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

    //genre section
    override fun getGenreCardsFromServer(): LiveData<List<GenreCardDTO?>> {
        return liveDataToObserveGenres
    }

    override fun getGenreInternetAccess() {
        InternetLoader.loadGenreWithRetrofit(object : InternetLoader.Listener<List<GenreCardDTO?>> {
            override fun on(arg: List<GenreCardDTO?>) {
                liveDataToObserveGenres.value = arg
            }
        })
    }

    //filmByGenre section
    override fun getFilmByGenreFromServer(): LiveData<List<FilmByGenreCardDTO?>> {
        return liveDataToObserveFilmByGenre
    }

    override fun getFilmByGenreInternetAccess(genreId: Int?) {
        InternetLoader.loadFilmByGenreWithRetrofit(genreId,
            object : InternetLoader.Listener<List<FilmByGenreCardDTO?>> {
                override fun on(arg: List<FilmByGenreCardDTO?>) {
                    liveDataToObserveFilmByGenre.value = arg
                }
            })
    }

    override fun getFilmReviewFromServer(): LiveData<List<ReviewCardDTO?>> {
        return liveDataToObserveReview
    }

    override fun getFilmReviewInternetAccess(filmId: Int?) {
        InternetLoader.loadFilmReviews(filmId,
            object : InternetLoader.Listener<List<ReviewCardDTO?>> {
                override fun on(arg: List<ReviewCardDTO?>) {
                    liveDataToObserveReview.value = arg
                }
            })
    }


}