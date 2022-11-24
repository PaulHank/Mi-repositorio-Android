package com.liceolapaz.dam.pae;

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.liceolapaz.dam.pae.R.id
import com.liceolapaz.dam.pae.databinding.CrearJugadorBinding

class CrearJugador: AppCompatActivity() {

    private lateinit var binding: CrearJugadorBinding

    private lateinit var db: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CrearJugadorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.BarraDeInicio.root)
        binding.BarraDeInicio.root.title = binding.NombreEdit.text

        binding.BarraDeInicio.root.inflateMenu(R.menu.menu)
        supportActionBar?.setDisplayShowTitleEnabled(true)

        binding.cancelar
        binding.eliminar

        val bdd = BaseDeDatos(this, "BaseDeJugadores", null, 1)
        db = bdd.writableDatabase

        binding.aceptar.setOnClickListener {
            val nuevoInsert = ContentValues()
            nuevoInsert.put("codigo", binding.CodigoEdit.text.toString())
            nuevoInsert.put("nombre", binding.NombreEdit.text.toString())
            nuevoInsert.put("posicion",binding.PosicionSpinner.selectedItem.toString())
            nuevoInsert.put("precio",binding.PrecioEdit.text.toString())
            nuevoInsert.put("puntos",binding.PuntosEdit.text.toString())

            db.insert("Jugadores", null, nuevoInsert)
        }

        binding.cancelar.setOnClickListener {
            Log.i("Cancelar", "Return")
            finish()
        }
    }
}