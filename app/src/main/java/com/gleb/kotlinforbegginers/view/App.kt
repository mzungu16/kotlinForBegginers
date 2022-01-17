package com.gleb.kotlinforbegginers.view

import android.app.Application
import androidx.room.Room
import com.gleb.kotlinforbegginers.model.FilmDao
import com.gleb.kotlinforbegginers.model.MyDatabase

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        private var appInstance: App? = null
        private var db: MyDatabase? = null
        private var DB_NAME = "Room.db"

        fun getMyDatabase(): FilmDao {
            if (db == null) {
                synchronized(MyDatabase::class.java) {
                    if (db == null) {
                        appInstance?.let {
                            db = Room.databaseBuilder(
                                it.applicationContext,
                                MyDatabase::class.java,
                                DB_NAME
                            ).allowMainThreadQueries()
                                .build()
                        }
                    }
                }
            }
            return db!!.filmDao()
        }
    }
}