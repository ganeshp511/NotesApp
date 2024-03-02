package com.tools.notesapp.repository

import com.tools.notesapp.database.NoteDatabase
import com.tools.notesapp.model.Note

class NoteRepository(private val db:NoteDatabase) {
    //repository means it is a single source of data
    //we can use multiple data sources like local database, network, etc
    //we can use repository to decide from where to get the data
    suspend fun insertNote(note: Note)=db.getNoteDao().insertNote(note)
    suspend fun deleteNote(note:Note)=db.getNoteDao().deleteNote(note)
    suspend fun updateNote(note:Note)=db.getNoteDao().updateNote(note)

    fun getAllNotes()=db.getNoteDao().getAllNotes()
    fun searchNotes(query:String?)=db.getNoteDao().searchNote(query)

}