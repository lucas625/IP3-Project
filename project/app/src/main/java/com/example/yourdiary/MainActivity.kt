package com.example.yourdiary

import adapter.AnotacaoAdapter
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import addNote.AddNoteActivity
import addNote.NOTE_DATE
import addNote.NOTE_TEXT
import addNote.NOTE_TITLE
import android.view.View
import androidx.activity.viewModels
import anotacaoDetail.AnotacaoDetailActivity
import models.Anotacao
import noteList.NotesListViewModel
import noteList.NotesListViewModelFactory

const val ANOTACAO_ID = "anotacao id"

class MainActivity : AppCompatActivity() {
    private val newNoteActivityRequestCode = 1
    private val notesListViewModel by viewModels<NotesListViewModel> {
        NotesListViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val anotacaoAdapter = AnotacaoAdapter {anotacao -> adapterOnClick(anotacao)}
        val recyclerView = findViewById<RecyclerView>(R.id.list_of_anotacoes)
        recyclerView.adapter = anotacaoAdapter
        recyclerView.setHasFixedSize(true)

            // List live update to be fixed
        notesListViewModel.notesLiveData.observe(this, {
               it?.let {
                   anotacaoAdapter.submitList(it as MutableList<Anotacao>)
               }
       })


        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener {
            fabOnClick()
        }
    }

    private fun adapterOnClick(anotacao: Anotacao) {
        val intent = Intent(this, AnotacaoDetailActivity()::class.java)
        intent.putExtra(ANOTACAO_ID, anotacao.id)
        startActivity(intent)
    }

    private fun fabOnClick() {
        val intent = Intent(this, AddNoteActivity::class.java)
        startActivityForResult(intent, newNoteActivityRequestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        /* Inserts note into viewModel. */
        if (requestCode == newNoteActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->
                val noteTitle = data.getStringExtra(NOTE_TITLE)
                val noteText = data.getStringExtra(NOTE_TEXT)
                val noteDate = data.getStringExtra(NOTE_DATE)


                notesListViewModel.insertNote(noteTitle, noteText, noteDate)
            }
        }
    }
}