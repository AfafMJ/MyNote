package com.afaf.mynote.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.afaf.mynote.Database.NotesDatabase
import com.afaf.mynote.Model.Notes
import com.afaf.mynote.Repository.NotesRepository

class NotesViewModel(application: Application): AndroidViewModel(application) {
    val repository:NotesRepository

    init {
        val dao = NotesDatabase.getDatabaseInstance(application).myNotesDao()
        repository = NotesRepository(dao)
    }

    fun addNotes(notes: Notes){
        repository.insertNotes(notes)
    }
    fun getNotes():LiveData<List<Notes>> = repository.getAllNotes()

    fun deleteNotes(id: Int){
        repository.deleteNotes(id)
    }
    fun updateNote(notes:Notes){
        repository.updateNotes(notes)
    }
}