package com.liceolapaz.dam.pae

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem

// users\minicuenta\appdata\local\android\sdk\platform-tools
// adb connect localhost:5555
class DB: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.db)

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

