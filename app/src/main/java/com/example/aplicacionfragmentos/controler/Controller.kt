package com.example.aplicacionfragmentos.controler

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import com.example.aplicacionfragmentos.adapter.AdapterMusica
import com.example.aplicacionfragmentos.MainActivity
import com.example.aplicacionfragmentos.dao.DaoMusic
import com.example.aplicacionfragmentos.models.Musica

class Controller(val context: Context) {
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
        val myActivity = context as MainActivity
        adapterMusica = AdapterMusica(
            listMusicas,
            { pos -> delMusica(pos) },
            { pos -> updateMusica(pos) }
        )
        myActivity.binding.myRecyclerView.adapter = adapterMusica
    }

    private fun delMusica(position: Int) {
        if (position >= 0 && position < listMusicas.size) {
            val deletedMusica = listMusicas[position]

            val builder = AlertDialog.Builder(context)
            builder.setMessage("¿Deseas borrar la música ${deletedMusica.name}?")
                .setPositiveButton("Sí") { dialog, id ->
                    // Realiza la lógica de eliminación aquí
                    listMusicas.removeAt(position)
                    adapterMusica.notifyItemRemoved(position)
                    Toast.makeText(context, "Música eliminada: ${deletedMusica.name}", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("No") { dialog, id ->
                    dialog.dismiss()
                }

            // Crea y muestra el diálogo
            val alertDialog: AlertDialog = builder.create()
            alertDialog.show()
        }
    }

    private fun updateMusica(position: Int) {
        if (position >= 0 && position < listMusicas.size) {
            val updatedMusica = listMusicas[position]
            Toast.makeText(context, "Editando música: ${updatedMusica.name}", Toast.LENGTH_SHORT).show()
            // Puedes implementar la lógica de actualización aquí, por ejemplo, abrir un formulario de edición
        }
    }
}
