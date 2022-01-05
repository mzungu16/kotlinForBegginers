package com.gleb.kotlinforbegginers

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class MainViewModel : ViewModel() {
    private val liveDataToObserve: MutableLiveData<State> = MutableLiveData()
    private val repo: Repository = RepositoryImpl()

    fun getData(): LiveData<State> = liveDataToObserve

    fun getDataFromServer() {
        FilmLoader.load(object : FilmLoader.Listener<List<FactDTO?>> {
            override fun on(arg: List<FactDTO?>) {
                liveDataToObserve.value = State.Success(arg)
            }
        })
    }
}