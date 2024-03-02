package com.tools.notesapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tools.notesapp.repository.NoteRepository

class NoteViewModelFactory(val app:Application, private val noteRepository:NoteRepository):ViewModelProvider.Factory{
    //factory class is used to create the instance of viewmodel
    //it is used to pass the parameters to the viewmodel class
    //we can pass the parameters to the viewmodel class using factory class
    //we can also use dependency injection
    //we can also use factory class to create the instance of viewmodel
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoteViewModel(app,noteRepository) as T
    }
}