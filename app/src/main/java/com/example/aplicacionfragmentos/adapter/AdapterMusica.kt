package com.example.aplicacionfragmentos.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacionfragmentos.R
import com.example.aplicacionfragmentos.models.Musica

class AdapterMusica(
    var listMusica: MutableList<Musica>,
    var deleteOnClick: (Int) -> Unit,
    var updateOnClick: (Int) -> Unit,
    var showEditDialog: (Musica?, Int?) -> Unit
) : RecyclerView.Adapter<ViewHMusica>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHMusica {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layoutItemMusic = R.layout.item_music
        return ViewHMusica(
            layoutInflater.inflate(layoutItemMusic, parent, false),
            deleteOnClick,
            updateOnClick,
            showEditDialog
        )
    }

    override fun onBindViewHolder(holder: ViewHMusica, position: Int) {
        holder.renderize(listMusica[position])

        // Llama a la función de actualización y muestra el diálogo solo cuando se hace clik boton "btn_edit" ítem
        holder.binding.btnEdit.setOnClickListener {
            updateOnClick(position)
        }
    }

    override fun getItemCount(): Int = listMusica.size
}