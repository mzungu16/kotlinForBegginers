package com.gleb.kotlinforbegginers

import android.os.*
import android.util.Log
import androidx.annotation.MainThread
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

object FilmLoader {
    const val TAG = "myTag"
    private const val KEY = "43f0fb07d8e8120f4d5ef58a4ba90cb7"

    fun load(onCompleteListener: Listener<List<FactDTO?>>) {
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
                val filmCardDTO = Gson().fromJson(result, FilmCardDTO::class.java)
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

    interface Listener<T> {
        fun on(arg: T)
    }
}