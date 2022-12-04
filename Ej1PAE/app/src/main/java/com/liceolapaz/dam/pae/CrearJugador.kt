package com.liceolapaz.dam.pae

import android.annotation.SuppressLint
import android.app.Activity
import android.content.DialogInterface
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.liceolapaz.dam.pae.databinding.CrearJugadorBinding
import com.liceolapaz.dam.pae.jugador.Jugador
import com.liceolapaz.dam.pae.jugador.JugadorAdapter
import com.liceolapaz.dam.pae.jugador.JugadorProvider

class CrearJugador: AppCompatActivity() {

    private lateinit var binding: CrearJugadorBinding
    private var listaJugadoresEditable:MutableList<Jugador> = JugadorProvider.listaJugadores.toMutableList()
    private lateinit var adapter: JugadorAdapter
    private lateinit var db: SQLiteDatabase

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CrearJugadorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.BarraDeInicio.root)
        binding.BarraDeInicio.lblTitulo.text = "Nuevo jugador"

        binding.BarraDeInicio.root.inflateMenu(R.menu.menu)
        supportActionBar?.setDisplayShowTitleEnabled(false)

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

        val bdd = BaseDeDatos(this, "BaseDeJugadores", null, 1)
        db = bdd.writableDatabase

        binding.aceptar.setOnClickListener {
            crearDialogoAceptar()
        }

        binding.cancelar.setOnClickListener {
            crearDialogoCancelar()
        }

        binding.eliminar.setOnClickListener {
            crearDialogoEliminar()
        }

    }

    private fun crearDialogoEliminar() {
        val builder = android.app.AlertDialog.Builder(this@CrearJugador)
        builder.setTitle("Aceptar")
        builder.setMessage("El jugador se eliminará de la base de datos, ¿continuar?")
        builder.setPositiveButton("Si", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface, which: Int) {
                deleteJugador()
            }

            private fun deleteJugador() {
                val codigo = binding.CodigoEdit.text.toString()

                val sql = "DELETE jugador WHERE codigo='$codigo'"
                db.execSQL(sql)

                listaJugadoresEditable.removeAt(Integer.parseInt(binding.CodigoEdit.text.toString()))
                adapter.notifyItemRemoved(Integer.parseInt(binding.CodigoEdit.text.toString()))

            }
        })
            .setNegativeButton("No") { _, _ -> finish() }
            .setNeutralButton("Cancelar" ) { dialog, _ -> dialog.dismiss() }

        val dialog = builder.create()
        dialog.show()
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
            }

            private fun insertJugador() {
                val nombre = binding.NombreEdit.text.toString()
                val precio = binding.PrecioEdit.text.toString()
                val posicion = binding.PosicionesJ.selectedItem.toString()
                val puntos = binding.PuntosEdit.text.toString()

                val sql = "INSERT INTO Jugadores (nombre,precio,posicion,puntos) VALUES ('$nombre','$precio','$posicion','$puntos')"
                db.execSQL(sql)

                val jugador = Jugador(
                    Integer.parseInt(binding.CodigoEdit.text.toString()),
                    binding.NombreEdit.text.toString(),
                    binding.PosicionesJ.selectedItem.toString()
                        .substring(0, (binding.PosicionesJ.selectedItem.toString().length - 5)),
                    Integer.parseInt(binding.PrecioEdit.text.toString()),
                    Integer.parseInt(binding.PuntosEdit.text.toString())
                )
                listaJugadoresEditable.add((listaJugadoresEditable.size-1),jugador)
                adapter.notifyItemInserted(listaJugadoresEditable.size-1)
            }
        })
        .setNegativeButton("No") { _, _ -> finish() }
        .setNeutralButton("Cancelar" ) { dialog, _ -> dialog.dismiss() }

        val dialog = builder.create()
        dialog.show()
    }
}