package com.example.yourdiary

import adapter.AnotacaoAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase
import data_sources.loadAnotacoes

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dataset = loadAnotacoes(resources)
        val recyclerView = findViewById<RecyclerView>(R.id.list_of_anotacoes)
        recyclerView.adapter = AnotacaoAdapter(this, dataset)
        recyclerView.setHasFixedSize(true)


        var database = FirebaseDatabase.getInstance()

        val myRef = database.getReference("anotacoes")

        myRef.setValue("Test")
    }
}