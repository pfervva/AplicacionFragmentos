package com.example.aplicacionfragmentos.objects_models

import com.example.aplicacionfragmentos.ui.Musica.models.Musica
import org.json.JSONObject

object Repository {

    var listMusicas : MutableList<Musica> = mutableListOf()

    fun updateMusicasFromApiResponse(response: String) {
        // convertir la respuesta JSON en objetos
        val jsonResponse = JSONObject(response)
        val cancionesArray = jsonResponse.getJSONArray("canciones")

        // limpiar la lista actual para evitar duplicados
        listMusicas.clear()

        for (i in 0 until cancionesArray.length()) {
            val item = cancionesArray.getJSONObject(i)
            val musica = Musica(
                id = item.getInt("id"),
                name = item.getString("nombre"),
                artista = item.getString("artista"),
                image = item.getString("imagen")
            )
            listMusicas.add(musica)
        }
    }
}
