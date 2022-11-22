package com.liceolapaz.dam.pae

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class JugadorViewHolder(view:View):RecyclerView.ViewHolder(view){

    val nombreJu = view.findViewById<TextView>(R.id.NombreJugador)
    val precioJu = view.findViewById<TextView>(R.id.PrecioJugador)
    val posicionJu = view.findViewById<TextView>(R.id.PosicionJugador)
    val puntosJu = view.findViewById<TextView>(R.id.PuntosJugador)

    fun noseque(jugadorAlgo:Jugador) {
        nombreJu.text = jugadorAlgo.nombre
        precioJu.text = jugadorAlgo.precio.toString()
        posicionJu.text = jugadorAlgo.posicion
        puntosJu.text = jugadorAlgo.puntos.toString()
    }
}