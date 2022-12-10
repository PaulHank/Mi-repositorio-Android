package com.liceolapaz.dam.pae.jugador

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.liceolapaz.dam.pae.R

class JugadorViewHolder(view:View):RecyclerView.ViewHolder(view){

    val codigoJu : TextView = view.findViewById(R.id.CodigoJugador)
    val nombreJu: TextView = view.findViewById(R.id.NombreJugador)
    val precioJu: TextView = view.findViewById(R.id.PrecioJugador)
    val posicionJu: TextView = view.findViewById(R.id.PosicionJugador)
    val puntosJu: TextView = view.findViewById(R.id.PuntosJugador)

    fun noseque(
        jugadorAlgo: Jugador,
        onClickListener: (Jugador) -> Unit
    ) {
        codigoJu.text = jugadorAlgo.codigo.toString()
        nombreJu.text = jugadorAlgo.nombre
        precioJu.text = jugadorAlgo.precio.toString() + " €"
        posicionJu.text = jugadorAlgo.posicion
        puntosJu.text = jugadorAlgo.puntos.toString() + " puntos"
        itemView.setOnClickListener {onClickListener(jugadorAlgo)}

    }

}