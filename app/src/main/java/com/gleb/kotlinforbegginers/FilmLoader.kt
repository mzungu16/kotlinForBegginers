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

    fun load(): List<FactDTO?> {
        val handler = Handler(Looper.myLooper() ?: Looper.getMainLooper())
        var loadedList: List<FactDTO?> = listOf()
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
                    loadedList = filmCardDTO.results
                    Log.d(TAG, "Success $loadedList")
                }
            } catch (e: Exception) {
                Log.d(TAG, e.message.toString())
            } finally {
                urlConnection?.disconnect()

            }
        }.start()
        Log.d(TAG,"$loadedList")
        return loadedList
    }
}