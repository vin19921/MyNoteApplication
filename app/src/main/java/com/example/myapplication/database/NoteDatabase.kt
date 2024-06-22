package com.example.myapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.model.Note

// Database class that represents the SQLite database for the application
// @Database annotation specifies the entities (tables) and the version of the database
// - entities: The list of entities (tables) included in the database
// - version: The version number of the database schema, used for migrations
// - exportSchema: If true, Room will export the database schema into a JSON file, useful for documentation
@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {

    // Abstract method to get an instance of the NoteDao
    // This method will be implemented by Room at runtime
    abstract fun noteDao(): NoteDao
}