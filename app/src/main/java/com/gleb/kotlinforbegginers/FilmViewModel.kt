package com.gleb.kotlinforbegginers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class FilmViewModel : ViewModel() {
    private val liveDataToObserve: MutableLiveData<List<FilmCardDTO?>> = MutableLiveData()
    private val repo: Repository = RepositoryImpl()

    fun getFilmLiveData(): LiveData<List<FilmCardDTO?>> = Transformations.switchMap(liveDataToObserve) {
        repo.getFilmCardsFromServer()
    }

    fun setLiveDataValueMethod(arg: List<FilmCardDTO?>) {
        liveDataToObserve.value = arg
    }

    fun getFilmData() {
        repo.getFilmInternetAccess()
    }
}
