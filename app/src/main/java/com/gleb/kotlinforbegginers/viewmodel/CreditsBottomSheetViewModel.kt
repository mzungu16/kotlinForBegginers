package com.gleb.kotlinforbegginers.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.gleb.kotlinforbegginers.model.CreditsCardDTO
import com.gleb.kotlinforbegginers.model.Repository
import com.gleb.kotlinforbegginers.model.RepositoryImpl

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