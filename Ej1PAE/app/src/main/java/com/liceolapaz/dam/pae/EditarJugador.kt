package com.liceolapaz.dam.pae

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.liceolapaz.dam.pae.databinding.EditarJugadorBinding
import com.liceolapaz.dam.pae.jugador.Jugador
import com.liceolapaz.dam.pae.jugador.JugadorAdapter
import com.liceolapaz.dam.pae.jugador.JugadorProvider

class EditarJugador: AppCompatActivity() {

    private lateinit var binding: EditarJugadorBinding
    private var listaJugadoresEditable:MutableList<Jugador> = JugadorProvider.listaJugadores.toMutableList()
    private lateinit var adapter: JugadorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EditarJugadorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.BarraDeInicio.root)
        binding.BarraDeInicio.lblTitulo.text = binding.NombreEdit.text

        binding.BarraDeInicio.root.inflateMenu(R.menu.menu)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        //Spinner y sus contenidos
        ArrayAdapter.createFromResource(
            this,
            R.array.posicion,
            android.R.layout.simple_spinner_item
        ).also {
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

        //Base de datos abierta
        val bdd = BaseDeDatos(this, "BaseDeJugadores", null, 1)
        val db = bdd.writableDatabase

        //Boton de aceptar
        binding.aceptar.setOnClickListener {

        }

        //Boton de cancelar
        binding.cancelar.setOnClickListener {

        }
    }
}