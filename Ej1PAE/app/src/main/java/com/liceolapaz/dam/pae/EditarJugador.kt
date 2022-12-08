package com.liceolapaz.dam.pae

import android.app.Activity
import android.content.DialogInterface
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.liceolapaz.dam.pae.databinding.EditarJugadorBinding
import com.liceolapaz.dam.pae.jugador.Jugador
import com.liceolapaz.dam.pae.jugador.JugadorAdapter

class EditarJugador: AppCompatActivity() {

    private lateinit var binding: EditarJugadorBinding
    private var updater = DB().listaJugadores.toMutableList()
    private lateinit var adapter: JugadorAdapter
    private lateinit var dataBase: SQLiteDatabase

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
        dataBase = bdd.writableDatabase

        val cursor : Cursor = dataBase.rawQuery("SELECT * FROM Jugadores WHERE 'codigo' =3",null)
        if (cursor.moveToFirst()) {
            binding.CodigoEdit.setText(cursor.getInt(0))
            binding.NombreEdit.setText(cursor.getString(1))
            binding.PrecioEdit.setText(cursor.getInt(2))
            binding.PuntosEdit.setText(cursor.getInt(4))
        }

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
            }

            private fun deleteJugador() {
                val codigo = binding.CodigoEdit.text.toString()

                val sql = "DELETE jugador WHERE codigo='$codigo'"
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
                insertJugador()
                finish()
            }

            private fun insertJugador() {
                val codigo = Integer.parseInt(binding.CodigoEdit.text.toString())
                val nombre = binding.NombreEdit.text.toString()
                val precio = Integer.parseInt(binding.PrecioEdit.text.toString())
                val posicion = binding.PosicionesJ.selectedItem.toString()
                val puntos = Integer.parseInt(binding.PuntosEdit.text.toString())

                val sql = "UPDATE Jugadores SET 'nombre' = $nombre, 'precio' = $precio, 'posicion' = $posicion, 'puntos' = $puntos WHERE 'codigo' = $codigo"
                dataBase.execSQL(sql)

                val jugador = Jugador(
                    Integer.parseInt(binding.CodigoEdit.text.toString()),
                    binding.NombreEdit.text.toString(),
                    Integer.parseInt(binding.PrecioEdit.text.toString()),
                    binding.PosicionesJ.selectedItem.toString()
                        .substring(0, (binding.PosicionesJ.selectedItem.toString().length - 5)),
                    Integer.parseInt(binding.PuntosEdit.text.toString())
                )
                //Esto es el equivalente a hacer listaJugadoresEditable.set(Index, Objeto)
                updater.add(jugador)
                adapter.notifyItemChanged(Integer.parseInt(binding.CodigoEdit.text.toString())-1)
            }
        })
        builder.setNegativeButton("No") {_,_ -> finish()}
        builder.setNeutralButton("Cancelar") {dialog, _ -> dialog.dismiss()}
    }


}