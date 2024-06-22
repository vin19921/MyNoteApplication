package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.MyApplication
import com.example.myapplication.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class NoteViewModel : ViewModel() {

    // Access the NoteDao from the application database
    private val noteDao = MyApplication.database.noteDao()

    // Get a flow of all notes with sorting and grouping by category
    fun getCategoriesWithNotes(): Flow<Map<String, List<Note>>> {
        return noteDao.getAllNotesFlow()
            .map { notes ->
                notes.sortedByDescending { it.timestamp } // Sort notes by timestamp (descending)
                    .groupBy { it.category } // Group notes by category
            }
    }

    // Get a flow of categories with corresponding note counts
    fun getCategoriesWithNoteCounts(): Flow<Map<String, Int>> {
        return noteDao.getAllNotesFlow()
            .map { notes ->
                notes.groupBy { it.category } // Group notes by category
                    .mapValues { it.value.size } // Map each group to its size (number of notes)
            }
    }

    // Add a new note to the database
    fun addNote(content: String, category: String) {
        val note = Note(content = content, category = category)
        viewModelScope.launch(Dispatchers.IO) { // Launch coroutine in IO dispatcher
            noteDao.insert(note) // Insert the note using NoteDao
        }
    }

    // Delete all notes from the database
    fun deleteAllNotes() {
        viewModelScope.launch(Dispatchers.IO) { // Launch coroutine in IO dispatcher
            noteDao.deleteAllNotes() // Delete all notes using NoteDao
        }
    }
}

