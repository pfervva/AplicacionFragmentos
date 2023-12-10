package com.example.aplicacionfragmentos.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacionfragmentos.R
import com.example.aplicacionfragmentos.models.Musica

class AdapterMusica(private val listMusica: MutableList<Musica>) : RecyclerView.Adapter<ViewHMusica>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHMusica {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layoutItemMusic = R.layout.item_music
        return ViewHMusica(layoutInflater.inflate(layoutItemMusic, parent, false), this, listMusica)
    }

    override fun onBindViewHolder(holder: ViewHMusica, position: Int) {
        holder.renderize(listMusica[position])
    }

    override fun getItemCount(): Int = listMusica.size
}
