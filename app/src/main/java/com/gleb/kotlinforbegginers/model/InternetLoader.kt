package com.gleb.kotlinforbegginers.model

import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object InternetLoader {
    const val TAG = "myTag"
    private const val KEY = "43f0fb07d8e8120f4d5ef58a4ba90cb7"

    private val client: OkHttpClient = OkHttpClient.Builder()
        .callTimeout(1000, TimeUnit.MILLISECONDS)
        .connectTimeout(1000, TimeUnit.MILLISECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    private val retrofitObject: RetrofitInt = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(RetrofitInt::class.java)

    fun loadCreditsWithRetrofit(filmId: Int?, onCompleteListener: Listener<List<CreditsCardDTO?>>) {
        retrofitObject.getCredits(KEY, filmId, KEY).enqueue(object : Callback<CreditsDTO?> {
            override fun onResponse(call: Call<CreditsDTO?>, response: Response<CreditsDTO?>) {
                response.body()?.let {
                    onCompleteListener.on(it.cast)
                }
            }

            override fun onFailure(call: Call<CreditsDTO?>, t: Throwable) {
                Log.d(TAG, t.stackTraceToString())
            }
        })
    }

    fun loadFilmWithRetrofit(onCompleteListener: Listener<List<FilmCardDTO?>>) {
        retrofitObject.getFilms(KEY, KEY).enqueue(object : Callback<FilmDTO> {
            override fun onResponse(call: Call<FilmDTO>, response: Response<FilmDTO>) {
                response.body()?.let {
                    onCompleteListener.on(it.results)
                }
            }

            override fun onFailure(call: Call<FilmDTO>, t: Throwable) {
                Log.d(TAG, t.stackTraceToString())
            }

        })
    }

    interface Listener<T> {
        fun on(arg: T)
    }
}