package com.example.myapplication

import android.app.Application
import androidx.room.Room
import com.example.myapplication.database.NoteDatabase

class MyApplication : Application() {

    companion object {
        lateinit var database: NoteDatabase
            private set // Make sure database can only be set once
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            NoteDatabase::class.java,
            "note_database"
        ).build()
    }
}