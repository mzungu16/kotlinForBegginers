package com.gleb.kotlinforbegginers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BottomSheetViewModel : ViewModel() {
    private val liveDataToObserve: MutableLiveData<State> = MutableLiveData()
    private val repo: Repository = RepositoryImpl()
    fun getData(): LiveData<State> = liveDataToObserve
    fun getDataFromServer2(profile:Int?){
        FilmLoader.loadCredits(profile,object : FilmLoader.Listener<List<CastDTO?>>{
            override fun on(arg: List<CastDTO?>) {
                liveDataToObserve.value = State.SuccessToCredits(arg)
            }
        })
    }
}