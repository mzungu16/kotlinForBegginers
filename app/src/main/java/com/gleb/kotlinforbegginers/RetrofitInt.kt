package com.gleb.kotlinforbegginers

import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitInt {
    @GET("3/movie/popular")
    fun getFilms(
        @Header("KEY") token: String,
        @Query("api_key") key: String
    ): Call<FilmDTO>

    @GET("3/movie/{movie_id}/credits")
    fun getCredits(
        @Header("KEY") token: String,
        @Path("movie_id") id: Int?,
        @Query("api_key") key: String
    ): Call<CreditsDTO>
}