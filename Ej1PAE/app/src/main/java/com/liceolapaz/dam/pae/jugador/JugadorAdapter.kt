package com.liceolapaz.dam.pae.jugador

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.liceolapaz.dam.pae.R

class JugadorAdapter(
    private val listaJugadores:List<Jugador>,
    private val onClickListener:(Jugador) -> Unit
) : RecyclerView.Adapter<JugadorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JugadorViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
    return JugadorViewHolder(layoutInflater.inflate(R.layout.jugador_item, parent, false))
    }

    override fun onBindViewHolder(holder: JugadorViewHolder, position: Int) {
        val item = listaJugadores[position]
        holder.noseque(item, onClickListener)
    }

    override fun getItemCount(): Int = listaJugadores.size
}