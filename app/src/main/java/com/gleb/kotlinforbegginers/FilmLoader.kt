package com.gleb.kotlinforbegginers

import android.os.*
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.rxjava3.functions.Consumer
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.Exception
import java.net.URL
import java.util.concurrent.TimeUnit
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

object FilmLoader {
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

    fun load(onCompleteListener: Listener<List<FilmCardDTO?>>) {
        val handler = Handler(Looper.myLooper() ?: Looper.getMainLooper())

        Thread {
            var urlConnection: HttpsURLConnection? = null

            try {
                val uri =
                    URL("https://api.themoviedb.org/3/movie/popular?api_key=$KEY&language=en-US&page=1&region=US")

                urlConnection = uri.openConnection() as HttpsURLConnection
                urlConnection.apply {
                    addRequestProperty("KEY", KEY)
                    requestMethod = "GET"
                    readTimeout = 1000
                    connectTimeout = 1000
                }
                val reader = BufferedReader(InputStreamReader(urlConnection.inputStream))
                val result = reader.lines().collect(Collectors.joining("\n"))
                Log.d(TAG, "Result = $result")
                val filmCardDTO = Gson().fromJson(result, FilmDTO::class.java)
                Log.d(TAG, "FilmCardDTO = $filmCardDTO")
                handler.post {
                    onCompleteListener.on(filmCardDTO.results)
                }
            } catch (e: Exception) {
                Log.d(TAG, e.message.toString())
            } finally {
                urlConnection?.disconnect()
            }
        }.start()
    }

    fun loadCredits(filmId: Int?, onCompleteListener: Listener<List<CastDTO?>>) {
//        Thread.currentThread().join(2000)
        val handler = Handler(Looper.myLooper() ?: Looper.getMainLooper())
        Thread {
            var urlConnection2: HttpsURLConnection? = null
            try {
                val uri2 =
                    URL("https://api.themoviedb.org/3/movie/$filmId/credits?api_key=$KEY&language=en-US")
                urlConnection2 = uri2.openConnection() as HttpsURLConnection
                urlConnection2.apply {
                    addRequestProperty("KEY2", KEY)
                    requestMethod = "GET"
                    readTimeout = 1000
                    connectTimeout = 1000
                }
                val reader = BufferedReader(InputStreamReader(urlConnection2.inputStream))
                val result = reader.lines().collect(Collectors.joining("\n"))
                Log.d(TAG, "Result2 = $result")
                val creditsDTO = Gson().fromJson(result, CreditsDTO::class.java)
                Log.d(TAG, "CreditsDTO = $creditsDTO")
                handler.post {
                    onCompleteListener.on(creditsDTO.cast)
                }
            } catch (e: Exception) {
                Log.d(TAG, e.message.toString())
            } finally {
                urlConnection2?.disconnect()
            }
        }.start()
    }

    fun loadOkHttp(onCompleteListener: Listener<List<FilmCardDTO?>>) {
        val request: Request = Request.Builder()
            .get()
            .addHeader("KEY", KEY)
            .url("https://api.themoviedb.org/3/movie/popular?api_key=$KEY&language=en-US&page=1&region=US")
            .build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                Log.d(TAG,e.message.toString())
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                if (response.isSuccessful) {
                    val filmDTO = Gson().fromJson(response.body?.string(), FilmDTO::class.java)
                    onCompleteListener.on(filmDTO.results)
                } else {
                    Log.d(TAG, "FAIL")
                }
            }

        })
    }

    fun loadRetrofit(onCompleteListener: Listener<List<FilmCardDTO?>>) {
        retrofitObject.getFilms(KEY, KEY).enqueue(object : Callback<FilmDTO> {
            override fun onResponse(call: Call<FilmDTO>, response: Response<FilmDTO>) {
                if (response.isSuccessful) {
                    response.body()?.let { filmDTO ->
                        onCompleteListener.on(filmDTO.results)
                    }
                } else {
                    Log.d(FilmLoader.TAG, "FAIL")
                }
            }

            override fun onFailure(call: Call<FilmDTO>, t: Throwable) {
                Log.d(FilmLoader.TAG, t.message.toString())
            }

        })
        Thread.sleep(2000)
    }

    interface Listener<T> {
        fun on(arg: T)
    }
}

/*
.enqueue(object : Callback<FilmDTO> {
    override fun onResponse(call: Call<FilmDTO>, response: Response<FilmDTO>) {
        if (response.isSuccessful) {
            response.body()?.let { filmDTO ->
                onCompleteListener.on(filmDTO.results)
            }
        } else {
            Log.d(FilmLoader.TAG, "FAIL")
        }
    }

    override fun onFailure(call: Call<FilmDTO>, t: Throwable) {
        Log.d(FilmLoader.TAG, t.message.toString())
    }

})*/
