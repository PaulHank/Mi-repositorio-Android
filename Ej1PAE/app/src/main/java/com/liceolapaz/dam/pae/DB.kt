package com.liceolapaz.dam.pae

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// users\minicuenta\appdata\local\android\sdk\platform-tools
// adb connect localhost:5555
class DB: AppCompatActivity() {

    fun initRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.JugadoresRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = JugadorAdapter(JugadorProvider.listaJugadores)
    }

    fun checkNumPlayers() {
        val textbox = findViewById<TextView>(R.id.numeroJugadores)
        textbox.text = "?"
        TODO("Encontrar como llamar a la funcion getItemCount en este plano para enseñar cuantos jugadores hay en total")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.db)
        initRecyclerView()

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.BarraDeNavegacion)
        setSupportActionBar(toolbar)

        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.añadir -> {
                    Log.i("ActionBar", "Añadir")

                    val intent = Intent(this@DB, CrearUsuarios::class.java)
                    startActivity(intent)
                    true
                }
                else -> {
                    true
                }
            }
        }
        toolbar.inflateMenu(R.menu.menu)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

        override fun onCreateOptionsMenu(menu: Menu): Boolean {
            menuInflater.inflate(R.menu.menu, menu)
            return true
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
            R.id.añadir -> {
                Log.i("ActionBar", "Nuevo!")
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

