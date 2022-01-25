package com.gleb.kotlinforbegginers.viewmodel

import androidx.lifecycle.*
import com.gleb.kotlinforbegginers.R
import com.gleb.kotlinforbegginers.model.*
import com.gleb.kotlinforbegginers.view.App

class FilmViewModel : ViewModel() {
    private val liveDataToObserve: MutableLiveData<List<FilmCardDTO?>> = MutableLiveData()
    private val liveDataToObserve2: MutableLiveData<List<GenreCardDTO?>> = MutableLiveData()
    private val liveDataToObserve3: MutableLiveData<List<FilmByGenreCardDTO?>> = MutableLiveData()
    val repo: Repository = RepositoryImpl()

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

    fun setFilmLiveDataValueMethod(arg: List<FilmCardDTO?>) {
        liveDataToObserve.value = arg
        liveDataToObserve.value = repo.getAllFilmsFromDB(App.getMyDatabase())
    }

    fun setGenreLiveDataValueMethod(arg: List<GenreCardDTO?>) {
        liveDataToObserve2.value = arg
    }

    fun setFilmByGenreLiveDataValueMethod(arg: List<FilmByGenreCardDTO?>) {
        liveDataToObserve3.value = arg
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
