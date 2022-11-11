package com.liceolapaz.dam.tresenrayapae

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var lblTurno : TextView
    private lateinit var lblCasilla : TextView
    private lateinit var terTablero : TresEnRaya
    private lateinit var txtVictory : TextView
    private lateinit var btnClear : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtVictory = findViewById(R.id.txtVictory)
        txtVictory.visibility = View.INVISIBLE

        lblTurno = findViewById(R.id.lblTurno)

        btnClear = findViewById(R.id.btnClear)
        btnClear.setOnClickListener {
            terTablero.limpiarTablero()
        }

        lblCasilla = findViewById(R.id.lblCasilla)

        terTablero = findViewById(R.id.tablero)
        terTablero.setOnCasillaSeleccionadaListener { fila, columna ->
            lblCasilla.text = "Última casilla seleccionada: ($fila, $columna)"
            checkEnd()
        }
    }

        private fun checkEnd() {
            val ganador = terTablero.getGanador()
            if (terTablero.getGanador() = TresEnRaya.FICHA_X) {
                txtVictory.text = "Han ganado los circulos!"
            }
            if (ganador = TresEnRaya.FICHA_O) {
                txtVictory.text = "Han ganado las cruzes!"
            }
            else {
                txtVictory.text = ""
            }
    }
}