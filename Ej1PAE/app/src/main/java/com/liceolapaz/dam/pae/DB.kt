package com.liceolapaz.dam.pae

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.liceolapaz.dam.pae.JugadorProvider.Companion.listaJugadores
import com.liceolapaz.dam.pae.databinding.DbBinding

// C:\users\minicuenta\appdata\local\android\sdk\platform-tools
// adb connect localhost:5555
class DB: AppCompatActivity() {

    private lateinit var binding: DbBinding

    private fun initRecyclerView(){
        binding.JugadoresRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.JugadoresRecyclerView.adapter = JugadorAdapter(listaJugadores)
    }

    @SuppressLint("SetTextI18n")
    fun checkNumPlayers() {
        binding.numeroJugadores.text = "Numero de jugadores: " + listaJugadores.size
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DbBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        checkNumPlayers()

        /*
        Explicacion de este root de abajo:
        Cuando creas un binding.algo, ese objeto deja de ser ese algo y pasa a ser un
        "AlgoBinding", lo cual puede romper cosas, en mi caso, se cargo la toolbar en si.
        No todos los objetos a los que se les haga binding dejaran de funcionar, por ejemplo
        el textbox de arriba esta funcionando como debe sin necesidad de un .root, que ademas
        no puede usar como tal.

        Con la etiqueta .root, haces que se obtenga el objeto "Superior",
        que es Algo en si, en este caso, una toolbar.
        */

        setSupportActionBar(binding.BarraDeNavegacion.root)
        binding.BarraDeNavegacion.root.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.añadir -> {
                    Log.i("ActionBar", "Añadir")

                    val intent = Intent(this@DB, CrearJugador::class.java)
                    startActivity(intent)
                    true
                }
                else -> {
                    true
                }
            }
        }
        binding.BarraDeNavegacion.root.inflateMenu(R.menu.menu)
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

