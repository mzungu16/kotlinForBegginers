package com.gleb.kotlinforbegginers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val liveDataToObserve: MutableLiveData<State> = MutableLiveData()
    private val repo: Repository = RepositoryImpl()

    fun getData(): LiveData<State> = liveDataToObserve

    fun getDataFromLocalSource() {
        liveDataToObserve.value = (State.Success(repo.getFilmCardsFromLocalStorage()))
    }
}