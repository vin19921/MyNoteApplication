package com.example.myapplication.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.model.Note
import kotlinx.coroutines.flow.Flow

// Data Access Object (DAO) for the Note entity
@Dao
interface NoteDao {

    // Inserts a new note into the database
    // This function is a suspend function, meaning it can be called from a coroutine
    // or another suspend function.
    @Insert
    suspend fun insert(note: Note)

    // Retrieves all notes from the database as a Flow of a list of notes
    // Flow is a cold stream that can be observed for changes, making it suitable
    // for observing live updates to the data.
    @Query("SELECT * FROM notes")
    fun getAllNotesFlow(): Flow<List<Note>>

    // Deletes all notes from the database
    // This is a suspend function and should be called from a coroutine or another suspend function.
    @Query("DELETE FROM notes")
    suspend fun deleteAllNotes()
}