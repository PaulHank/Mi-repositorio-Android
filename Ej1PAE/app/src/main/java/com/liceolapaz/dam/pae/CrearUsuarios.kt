package com.liceolapaz.dam.pae;

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.core.view.indices
import com.liceolapaz.dam.pae.R.id
import kotlin.system.exitProcess

class CrearUsuarios: AppCompatActivity() {
    private lateinit var Codigo : EditText
    private lateinit var Nombre : EditText
    private lateinit var Posicion : Spinner
    private lateinit var Precio : EditText
    private lateinit var Puntos : EditText
    private lateinit var Aceptar : Button
    private lateinit var Cancelar : Button
    private lateinit var Eliminar : Button

    private lateinit var db: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crear_jugador)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(id.BarraDeInicio)
        setSupportActionBar(toolbar)
        toolbar.title = Nombre.text

        toolbar.inflateMenu(R.menu.menu)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        Codigo = findViewById(id.CodigoEdit)
        Nombre = findViewById(id.NombreEdit)
        Posicion = findViewById(id.PosicionSpinner)
        Precio = findViewById(id.PrecioEdit)
        Puntos = findViewById(id.PuntosEdit)

        Aceptar = findViewById(id.aceptar)
        Cancelar = findViewById(id.cancelar)
        Eliminar = findViewById(id.eliminar)

        val bdd = BaseDeDatos(this, "BaseDeJugadores", null, 1)
        db = bdd.writableDatabase

        Aceptar.setOnClickListener {
            val codigo = Codigo.text.toString()
            val nombre = Nombre.text.toString()
            val posicion = Posicion.selectedItem.toString()
            val precio = Precio.text.toString()
            val puntos = Puntos.text.toString()

            val nuevoInsert = ContentValues()
            nuevoInsert.put("codigo", codigo)
            nuevoInsert.put("nombre", nombre)
            nuevoInsert.put("posicion",posicion)
            nuevoInsert.put("precio",precio)
            nuevoInsert.put("puntos",puntos)

            db.insert("Jugadores", null, nuevoInsert)
        }
    }


}
