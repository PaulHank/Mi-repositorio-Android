package com.example.miholamundoandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SaludoActivity : AppCompatActivity() {

    private lateinit var textoSaludo : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saludo)

        textoSaludo = findViewById(R.id.textoSaludo)

        val saludo = intent.getStringExtra("NOMBRE")

        textoSaludo.text = "Hola $saludo"
    }
}