package com.example.aplicacionfragmentos.ui.Musica

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplicacionfragmentos.databinding.ItemMusicBinding
import com.example.aplicacionfragmentos.ui.Musica.models.Musica
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AdapterMusica @Inject constructor(
    private var listMusica: List<Musica>,
    private val onEdit: (Musica, Int) -> Unit,
    private val onDelete: (Int) -> Unit
) : RecyclerView.Adapter<AdapterMusica.ViewHMusica>() {

    fun updateList(newList: List<Musica>) {
        listMusica = newList
        notifyDataSetChanged()
    }

    inner class ViewHMusica(private val binding: ItemMusicBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(musica: Musica, position: Int) {
            binding.txtSongTitle.text = musica.name
            binding.txtArtists.text = musica.artita
            Glide.with(binding.root)
                .load(musica.image)
                .into(binding.ivMusic)

            binding.btnDelete.setOnClickListener {
                onDelete(position)
            }

            binding.btnEdit.setOnClickListener {
                onEdit(musica, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHMusica {
        val binding = ItemMusicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHMusica(binding)
    }

    override fun onBindViewHolder(holder: ViewHMusica, position: Int) {
        holder.bind(listMusica[position], position)
    }

    override fun getItemCount(): Int = listMusica.size
}
