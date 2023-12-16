package com.example.aplicacionfragmentos.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplicacionfragmentos.databinding.ItemMusicBinding
import com.example.aplicacionfragmentos.models.Musica

class ViewHMusica(
    view: View,
    var deleteOnClick: (Int) -> Unit,
    var updateOnClick: (Int) -> Unit
) : RecyclerView.ViewHolder(view) {

    lateinit var binding: ItemMusicBinding

    init {
        binding = ItemMusicBinding.bind(view)

        binding.btnDelete.setOnClickListener {
            deleteOnClick(adapterPosition)
        }

        binding.btnEdit.setOnClickListener {
            updateOnClick(adapterPosition)
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