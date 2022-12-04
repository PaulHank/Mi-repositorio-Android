package com.liceolapaz.dam.pae.jugador

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.liceolapaz.dam.pae.R

class JugadorViewHolder(view:View):RecyclerView.ViewHolder(view){

    private val nombreJu: TextView = view.findViewById(R.id.NombreJugador)
    private val precioJu: TextView = view.findViewById(R.id.PrecioJugador)
    private val posicionJu: TextView = view.findViewById(R.id.PosicionJugador)
    private val puntosJu: TextView = view.findViewById(R.id.PuntosJugador)

    fun noseque(
        jugadorAlgo: Jugador,
        onClickListener: (Jugador) -> Unit
    ) {
        nombreJu.text = jugadorAlgo.nombre
        precioJu.text = jugadorAlgo.precio.toString() + " €"
        posicionJu.text = jugadorAlgo.posicion
        puntosJu.text = jugadorAlgo.puntos.toString() + " puntos"
        itemView.setOnClickListener {onClickListener(jugadorAlgo)}
    }

}