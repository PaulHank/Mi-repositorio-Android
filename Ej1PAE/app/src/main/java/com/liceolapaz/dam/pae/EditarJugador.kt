package com.liceolapaz.dam.pae


import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.liceolapaz.dam.pae.databinding.EditarJugadorBinding

class EditarJugador: AppCompatActivity() {

    private lateinit var binding: EditarJugadorBinding
    private lateinit var dataBase: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EditarJugadorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.BarraDeInicio.root)


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
        //Codigo del jugador ✔
        binding.CodigoEdit.setText(intent.getStringExtra("Codigo"))

        //Nombre del jugador ✔
        binding.NombreEdit.setText(intent.getStringExtra("Nombre"))

        //Precio del jugador ✔
        binding.PrecioEdit.setText(intent.getStringExtra("Precio"))

        //Posicion del jugador ✖
        if (intent.getStringExtra("Posicion").contentEquals("Portero (PT)")) {
            binding.PosicionesJ.setSelection(0)
        }
        if (intent.getStringExtra("Posicion").contentEquals("Defensa (DF)")) {
            binding.PosicionesJ.setSelection(1)
        }
        if (intent.getStringExtra("Posicion").contentEquals("Mediocampista (MC)")) {
            binding.PosicionesJ.setSelection(2)
        }
        if (intent.getStringExtra("Posicion").contentEquals("Delantero (DL)")) {
            binding.PosicionesJ.setSelection(3)
        }

        //Puntos del jugador ✔
        binding.PuntosEdit.setText(intent.getStringExtra("Puntos"))

        binding.BarraDeInicio.lblTitulo.text = binding.NombreEdit.text

        //Base de datos abierta
        val bdd = BaseDeDatos(this, "BaseDeJugadores", null, 1)
        dataBase = bdd.writableDatabase

        //Boton de aceptar
        binding.aceptar.setOnClickListener {
            crearDialogoAceptar()
        }

        //Boton de cancelar
        binding.cancelar.setOnClickListener {
            crearDialogoCancelar()
        }

        //Boton de eliminar
        binding.eliminar.setOnClickListener {
            crearDialogoEliminar()
        }
    }

    private fun crearDialogoEliminar() {
        val builder = android.app.AlertDialog.Builder(this@EditarJugador)
        builder.setTitle("Aceptar")
        builder.setMessage("El jugador se eliminará de la base de datos, ¿continuar?")
        builder.setPositiveButton("Si", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface, which: Int) {
                deleteJugador()
                val intent = Intent(this@EditarJugador, DB::class.java)
                startActivity(intent)
            }

            private fun deleteJugador() {
                val codigo = binding.CodigoEdit.text.toString()

                val sql = "DELETE FROM Jugadores WHERE codigo='$codigo'"
                dataBase.execSQL(sql)

            }
        })
            .setNegativeButton("No") { _, _ -> finish() }
            .setNeutralButton("Cancelar" ) { dialog, _ -> dialog.dismiss() }

        val dialog = builder.create()
        dialog.show()
    }

    private fun crearDialogoCancelar() {
        val builder = android.app.AlertDialog.Builder(this@EditarJugador)
        builder.setTitle("Cancelar")
        builder.setMessage("Los cambios no se aplicarán, ¿quieres continuar?")
        builder.setPositiveButton("Si") { _, _ -> finish() }
        builder.setNegativeButton("No") { dialog, _ -> dialog.dismiss() }

        val dialog = builder.create()
        dialog.show()
    }

    private fun crearDialogoAceptar() {
        val builder = android.app.AlertDialog.Builder(this@EditarJugador)
        builder.setTitle("Aceptar")
        builder.setMessage("Los datos se actualizarán en la base de datos, ¿continuar?")
        builder.setPositiveButton("Si", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface, which: Int) {
                updateJugador()
                val intent = Intent(this@EditarJugador, DB::class.java)
                startActivity(intent)
            }

            private fun updateJugador() {
                val codigo = Integer.parseInt(binding.CodigoEdit.text.toString())
                val nombre = binding.NombreEdit.text.toString()
                val precio = Integer.parseInt(binding.PrecioEdit.text.toString())
                val posicion = binding.PosicionesJ.selectedItem.toString()
                val puntos = Integer.parseInt(binding.PuntosEdit.text.toString())

                val sql = "UPDATE Jugadores SET nombre = '$nombre', precio = '$precio', posicion = '$posicion', puntos = '$puntos' WHERE codigo = '$codigo'"
                dataBase.execSQL(sql)
            }
        })
        builder.setNegativeButton("No") {_,_ -> finish()}
        builder.setNeutralButton("Cancelar") {dialog, _ -> dialog.dismiss()}

        val dialog = builder.create()
        dialog.show()
    }


}