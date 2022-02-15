package com.gleb.kotlinforbegginers.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.gleb.kotlinforbegginers.model.FilmCardDTO
import com.gleb.kotlinforbegginers.model.Repository
import com.gleb.kotlinforbegginers.model.RepositoryImpl
import com.gleb.kotlinforbegginers.model.ReviewCardDTO

class ReviewViewModel : ViewModel() {
    private val liveDataToObserve: MutableLiveData<List<ReviewCardDTO?>> = MutableLiveData()
    private val repo: Repository = RepositoryImpl()

    fun getFilmReviewLiveData(): LiveData<List<ReviewCardDTO?>> = Transformations.switchMap(liveDataToObserve){
        repo.getFilmReviewFromServer()
    }

    fun setFilmReviewLiveData(){
        liveDataToObserve.value = getFilmReviewLiveData().value
    }

    fun getFilmReviewData(filmId:Int?){
        repo.getFilmReviewInternetAccess(filmId)
    }

}