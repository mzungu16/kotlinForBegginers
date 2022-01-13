package com.gleb.kotlinforbegginers
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val liveDataToObserve: MutableLiveData<List<FilmCardDTO?>> = MutableLiveData()
    private val repo: Repository = RepositoryImpl()

    fun getData(): LiveData<List<FilmCardDTO?>> = liveDataToObserve

    fun getFilmDataFromServer() {
        InternetLoader.loadFilmWithRetrofit(object : InternetLoader.Listener<List<FilmCardDTO?>> {
            override fun on(arg: List<FilmCardDTO?>) {
                liveDataToObserve.value = arg
            }
        })
    }
}
/*
class GenreViewModel:ViewModel(){
    private val liveDataToObserve2: MutableLiveData<State> = MutableLiveData()
    fun getData2(): LiveData<State> = liveDataToObserve2
    fun getGenreDataFromServer() {
        InternetLoader.loadGenresWithRetrofit(object : InternetLoader.Listener<List<GenreCardDTO?>> {
            override fun on(arg: List<GenreCardDTO?>) {
                liveDataToObserve2.value = State.SuccessToGenres(arg)
            }
        })
    }
}*/
