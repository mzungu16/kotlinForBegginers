package com.gleb.kotlinforbegginers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class CreditsBottomSheetViewModel : ViewModel() {
    private val liveDataToObserve: MutableLiveData<List<CreditsCardDTO?>> = MutableLiveData()
    private val repo: Repository = RepositoryImpl()

    fun getLiveData(): LiveData<List<CreditsCardDTO?>> =
        Transformations.switchMap(liveDataToObserve) {
            repo.getCreditCardsFromServer()
        }

    fun setLiveDataValue(arg: List<CreditsCardDTO?>) {
        liveDataToObserve.value = arg
    }

    fun getCreditData(profileId: Int?) {
        repo.getCreditInternetAccess(profileId)
    }
}