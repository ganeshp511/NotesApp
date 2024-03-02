package com.tools.notesapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.tools.notesapp.model.Note

@Dao
interface NoteDao {
    //if same id is found then replace it (onConflict= OnConflictStrategy.REPLACE)
    @Insert(onConflict= OnConflictStrategy.REPLACE)
    //suspend function is used to make the function run in background thread(couroutine)
    suspend fun insertNote(note: Note)

    @Update
    suspend fun updateNote(note:Note)

    @Delete
    suspend fun deleteNote(note:Note)
    //recent note will be shown first
    @Query("SELECT * FROM NOTES ORDER BY id DESC")
    fun getAllNotes():LiveData<List<Note>>

    //if noteTitle or noteDesc contains the query then return the note
    @Query("SELECT * FROM NOTES WHERE noteTitle LIKE:query OR noteDesc LIKE:query")
    //? is used to make the query nullable (if no note is found then return null)
    fun searchNote(query:String?):LiveData<List<Note>>
}