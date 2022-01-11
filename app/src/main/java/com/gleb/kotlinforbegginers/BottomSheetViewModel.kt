package com.gleb.kotlinforbegginers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BottomSheetViewModel : ViewModel() {
    private val liveDataToObserve: MutableLiveData<State> = MutableLiveData()
    private val repo: Repository = RepositoryImpl()
    fun getData(): LiveData<State> = liveDataToObserve
    fun getDataFromServer2(profile:Int?){
        InternetLoader.loadCreditsWithRetrofit(profile,object : InternetLoader.Listener<List<CreditsCardDTO?>>{
            override fun on(arg: List<CreditsCardDTO?>) {
                liveDataToObserve.value = State.SuccessToCredits(arg)
            }
        })
    }
}