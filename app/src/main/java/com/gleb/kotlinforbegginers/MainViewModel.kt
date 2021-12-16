package com.gleb.kotlinforbegginers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class MainViewModel : ViewModel() {
    private val liveDataToObserve: MutableLiveData<State> = MutableLiveData()
    fun getData(): LiveData<State> = liveDataToObserve
    fun getCard() {

        liveDataToObserve.value = State.Loading

        Thread{
            Thread.sleep(2000)
            if (Random.nextBoolean()){
                liveDataToObserve.postValue(State.Success(Card(R.drawable.lord,"Lord Of the Rings")))
            }
            else{
                liveDataToObserve.postValue(State.Error(Exception("Check internet")))
            }
        }.start()

    }
}