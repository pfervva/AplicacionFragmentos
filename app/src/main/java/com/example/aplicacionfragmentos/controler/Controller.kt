package com.example.aplicacionfragmentos.controler

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacionfragmentos.R
import com.example.aplicacionfragmentos.adapter.AdapterMusica
import com.example.aplicacionfragmentos.dao.DaoMusic
import com.example.aplicacionfragmentos.models.Musica

class Controller(val context: Context, val recyclerView: RecyclerView) {
    lateinit var listMusicas: MutableList<Musica>
    lateinit var adapterMusica: AdapterMusica

    init {
        initData()
    }

    fun initData() {
        listMusicas = DaoMusic.myDao.getDataHotels().toMutableList()
    }

    fun loggOut() {
        Toast.makeText(context, "He mostrado los datos en pantalla", Toast.LENGTH_LONG).show()
        listMusicas.forEach {
            println(it)
        }
    }

    fun setAdapter() {
        adapterMusica = AdapterMusica(
            listMusicas,
            { pos -> delMusica(pos) },
            { pos -> updateMusica(pos) },
            { musica, position -> showEditDialog(musica, position) }
        )
        recyclerView.adapter = adapterMusica
    }

    private fun delMusica(position: Int) {
        if (position >= 0 && position < listMusicas.size) {
            val deletedMusica = listMusicas[position]

            val builder = AlertDialog.Builder(context)
            builder.setMessage("¿Deseas borrar la música ${deletedMusica.name}?")
                .setPositiveButton("Sí") { _, _ ->
                    // Realiza la lógica de eliminación aquí
                    listMusicas.removeAt(position)
                    adapterMusica.notifyItemRemoved(position)
                    Toast.makeText(context, "Música eliminada: ${deletedMusica.name}", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("No") { dialog, _ ->
                    dialog.dismiss()
                }

            // Crea y muestra el diálogo
            val alertDialog: AlertDialog = builder.create()
            alertDialog.show()
        }
    }

    private fun updateMusica(position: Int) {
        if (position >= 0 && position < listMusicas.size) {
            // Mostrar el diálogo de edición
            showEditDialog(listMusicas[position], position)
        }
    }

    internal fun showEditDialog(musica: Musica?, position: Int?) {
        val builder = AlertDialog.Builder(context)
        val inflater = LayoutInflater.from(context)
        val dialogView = inflater.inflate(R.layout.dialog_add_edit, null)
        builder.setView(dialogView)

        val editTextName = dialogView.findViewById<EditText>(R.id.editTextName)
        val editTextArtist = dialogView.findViewById<EditText>(R.id.editTextArtist)
        val editTextImage = dialogView.findViewById<EditText>(R.id.editTextImage)

        if (musica != null) {
            // Si es una edición, cargamos los datos existentes
            editTextName.setText(musica.name)
            editTextArtist.setText(musica.artita)
            editTextImage.setText(musica.image)
        }

        builder.setPositiveButton("Aceptar") { _, _ ->
            // Obtener los valores ingresados en el diálogo
            val name = editTextName.text.toString()
            val artist = editTextArtist.text.toString()
            val image = editTextImage.text.toString()

            if (position == null) {
                // Agregar nueva canción
                val newMusica = Musica(name, artist, image)
                listMusicas.add(newMusica)
                adapterMusica.notifyItemInserted(listMusicas.size - 1)
            } else {
                // Editar canción existente
                if (position >= 0 && position < listMusicas.size) {
                    listMusicas[position] = Musica(name, artist, image)
                    adapterMusica.notifyItemChanged(position)
                }
            }
        }

        builder.setNegativeButton("Cancelar") { dialog, _ ->
            dialog.dismiss()
        }

        builder.create().show()
    }
}