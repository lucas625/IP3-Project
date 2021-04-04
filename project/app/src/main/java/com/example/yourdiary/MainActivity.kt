package com.example.yourdiary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import data_sources.DataSource

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val targetText = findViewById<TextView>(R.id.contagem_de_anotacoes)
        targetText.text = DataSource().loadAffirmations().size.toString()
    }
}