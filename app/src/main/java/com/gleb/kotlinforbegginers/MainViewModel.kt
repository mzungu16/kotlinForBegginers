package com.gleb.kotlinforbegginers
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val liveDataToObserve: MutableLiveData<State> = MutableLiveData()
    private val repo: Repository = RepositoryImpl()

    fun getData(): LiveData<State> = liveDataToObserve

    fun getFilmDataFromServer() {
        InternetLoader.loadFilmWithRetrofit(object : InternetLoader.Listener<List<FilmCardDTO?>> {
            override fun on(arg: List<FilmCardDTO?>) {
                liveDataToObserve.value = State.Success(arg)
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
