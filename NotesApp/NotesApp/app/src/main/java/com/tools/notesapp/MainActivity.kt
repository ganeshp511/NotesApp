package com.tools.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.tools.notesapp.database.NoteDatabase
import com.tools.notesapp.repository.NoteRepository
import com.tools.notesapp.viewmodel.NoteViewModel
import com.tools.notesapp.viewmodel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var noteViewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
    }
    private fun setupViewModel(){
        val noteRepository= NoteRepository(NoteDatabase(this))
        val viewModelProviderFactory= NoteViewModelFactory(application,noteRepository)
        noteViewModel= ViewModelProvider(this,viewModelProviderFactory)[NoteViewModel::class.java]


    }
}