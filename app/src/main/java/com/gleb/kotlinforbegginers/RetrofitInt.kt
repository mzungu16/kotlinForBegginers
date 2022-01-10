package com.gleb.kotlinforbegginers

import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RetrofitInt {
    //observable FilmDTO
    @GET("3/movie/popular")
    fun getFilms(
        @Header("KEY") token: String,
        @Query("api_key") key: String
    ): Call<FilmDTO>
}