package com.liceolapaz.dam.pae

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.liceolapaz.dam.pae.databinding.CrearJugadorBinding
import com.liceolapaz.dam.pae.jugador.Jugador
import com.liceolapaz.dam.pae.jugador.JugadorAdapter

class CrearJugador: AppCompatActivity() {

    private lateinit var binding: CrearJugadorBinding
    private lateinit var db: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CrearJugadorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.BarraDeInicio.root)
        binding.BarraDeInicio.lblTitulo.text = "Nuevo jugador"

        binding.BarraDeInicio.root.inflateMenu(R.menu.menu)
        supportActionBar?.setDisplayShowTitleEnabled(false)


        //Spinner y sus contenidos
        ArrayAdapter.createFromResource(this, R.array.posicion, android.R.layout.simple_spinner_item).also {
            adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.PosicionesJ.adapter = adapter
        }

        class SpinnerActivity : Activity(), AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                binding.PosicionesJ.getItemAtPosition(pos)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }


        //Base de datos abierta.
        val bdd = BaseDeDatos(this, "BaseDeJugadores", null, 1)
        db = bdd.writableDatabase


        //Boton de aceptar.
        binding.aceptar.setOnClickListener {
            crearDialogoAceptar()
        }


        //Boton de cancelar
        binding.cancelar.setOnClickListener {
            crearDialogoCancelar()
        }

    }


    private fun crearDialogoCancelar() {
        val builder = android.app.AlertDialog.Builder(this@CrearJugador)
        builder.setTitle("Cancelar")
        builder.setPositiveButton("Si") { _, _ -> finish() }
        builder.setNegativeButton("No") { dialog, _ -> dialog.dismiss() }

        val dialog = builder.create()
        dialog.show()
    }

    private fun crearDialogoAceptar() {
        val builder = android.app.AlertDialog.Builder(this@CrearJugador)
        builder.setTitle("Aceptar")
        builder.setMessage("Los datos se guardaran en la base de datos, ¿continuar?")
        builder.setPositiveButton("Si", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface, which: Int) {
                insertJugador()
                val intent = Intent(this@CrearJugador, DB::class.java)
                startActivity(intent)
            }

            private fun insertJugador() {
                val nombre = binding.NombreEdit.text.toString()
                val precio = binding.PrecioEdit.text.toString()
                val posicion = binding.PosicionesJ.selectedItem.toString()
                val puntos = binding.PuntosEdit.text.toString()

                val sql = "INSERT INTO Jugadores (nombre,precio,posicion,puntos) VALUES ('$nombre','$precio','$posicion','$puntos')"
                db.execSQL(sql)
            }
        })
        .setNegativeButton("No") { _, _ -> finish() }
        .setNeutralButton("Cancelar" ) { dialog, _ -> dialog.dismiss() }

        val dialog = builder.create()
        dialog.show()
    }
}