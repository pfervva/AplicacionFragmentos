package com.example.aplicacionfragmentos.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplicacionfragmentos.databinding.ItemMusicBinding
import com.example.aplicacionfragmentos.models.Musica

class ViewHMusica(view: View, private val adapter: AdapterMusica, private val musicaList: MutableList<Musica>) :
    RecyclerView.ViewHolder(view) {

    lateinit var binding: ItemMusicBinding

    init {
        binding = ItemMusicBinding.bind(view)
        binding.btnEdit.setOnClickListener {
            // Implementa la lógica para editar el hotel si es necesario
        }
        binding.btnDelete.setOnClickListener {
            // Obtiene la posición del hotel en la lista
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                // Remueve el hotel de la lista
                musicaList.removeAt(position)
                // Notifica al adaptador sobre el cambio en los datos
                adapter.notifyItemRemoved(position)
            }
        }
    }

    // Método que se encarga de mapear los item por propiedad del modelo.
    fun renderize(musica: Musica) {
        binding.txtSongTitle.text = musica.name
        binding.txtArtists.text = musica.artita

        Glide.with(itemView.context)
            .load(musica.image)
            .centerCrop()
            .into(binding.ivMusic)
    }
}

