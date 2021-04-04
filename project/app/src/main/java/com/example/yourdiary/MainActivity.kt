package com.example.yourdiary

import adapter.AnotacaoAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import data_sources.DataSource

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dataset = DataSource().loadAnotacoes()
        val recyclerView = findViewById<RecyclerView>(R.id.list_of_anotacoes)
        recyclerView.adapter = AnotacaoAdapter(this, dataset)
        recyclerView.setHasFixedSize(true)
    }
}