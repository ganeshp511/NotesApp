package com.tools.notesapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import com.tools.notesapp.R
import com.tools.notesapp.databinding.FragmentAddNoteBinding
import com.tools.notesapp.MainActivity
import com.tools.notesapp.model.Note
import com.tools.notesapp.viewmodel.NoteViewModel

class AddNoteFragment : Fragment(R.layout.fragment_add_note), MenuProvider {
    private var addNoteBinding: FragmentAddNoteBinding?=null
    private val binding get() = addNoteBinding!!

    private lateinit var noteViewModel: NoteViewModel
    private lateinit var addNoteView:View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        addNoteBinding= FragmentAddNoteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost =requireActivity()
        menuHost.addMenuProvider(this,viewLifecycleOwner,Lifecycle.State.STARTED)
        noteViewModel= (activity as MainActivity).noteViewModel
        addNoteView=view

    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.menu_add_note,menu)
    }



    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId){
            R.id.saveMenu->{
                saveNote(addNoteView)
                true
            }
            else->false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        addNoteBinding=null
    }

    private fun saveNote(view:View){
        val noteTitle=binding.addNoteTitle.text.toString().trim()
        val noteDesc=binding.addNoteDesc.text.toString().trim()
        if(noteTitle.isNotEmpty()){
            val note= Note(0,noteTitle,noteDesc)
            noteViewModel.addNote(note)
            Toast.makeText(context,"Note Saved",Toast.LENGTH_SHORT).show()
            view.findNavController().popBackStack(R.id.homeFragment2,false)
        }else{
            Toast.makeText(context,"Please enter note title",Toast.LENGTH_SHORT).show()
        }
    }
}