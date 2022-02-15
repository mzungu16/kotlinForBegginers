package com.gleb.kotlinforbegginers.viewmodel

import androidx.lifecycle.*
import com.gleb.kotlinforbegginers.model.*

class FilmViewModel : ViewModel() {
    private val liveDataToObserve: MutableLiveData<List<FilmCardDTO?>> = MutableLiveData()
    private val liveDataToObserve2: MutableLiveData<List<GenreCardDTO?>> = MutableLiveData()
    private val liveDataToObserve3: MutableLiveData<List<FilmByGenreCardDTO?>> = MutableLiveData()
    private val repo: Repository = RepositoryImpl()

    //transfer viewLifecycleOwner to repository
    fun getFilmLiveData(): LiveData<List<FilmCardDTO?>> =
        Transformations.switchMap(liveDataToObserve) {
            repo.getFilmCardsFromServer()
        }

    fun getGenreLiveData(): LiveData<List<GenreCardDTO?>> =
        Transformations.switchMap(liveDataToObserve2) {
            repo.getGenreCardsFromServer()
        }

    fun getFilmByGenreLiveData(): LiveData<List<FilmByGenreCardDTO?>> =
        Transformations.switchMap(liveDataToObserve3) {
            repo.getFilmByGenreFromServer()
        }

    fun setFilmLiveDataValueMethod() {
        liveDataToObserve.value = getFilmLiveData().value
    }

    fun setGenreLiveDataValueMethod() {
        liveDataToObserve2.value = getGenreLiveData().value
    }

    fun setFilmByGenreLiveDataValueMethod() {
        liveDataToObserve3.value = getFilmByGenreLiveData().value
    }

    //internet access
    fun getFilmData() {
        repo.getFilmInternetAccess()
    }

    fun getGenreData() {
        repo.getGenreInternetAccess()
    }

    fun getFilmByGenreData(genreId: Int?) {
        repo.getFilmByGenreInternetAccess(genreId)
    }
}
