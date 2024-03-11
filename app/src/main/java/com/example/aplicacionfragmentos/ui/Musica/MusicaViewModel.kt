package com.example.aplicacionfragmentos.ui.Musica

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.aplicacionfragmentos.RetroFit.ApiClient
import com.example.aplicacionfragmentos.RetroFit.ApiService
import com.example.aplicacionfragmentos.objects_models.Repository
import com.example.aplicacionfragmentos.ui.Musica.models.Musica
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.aplicacionfragmentos.RetroFit.PreferenceHelper


class MusicaViewModel(application: Application) : AndroidViewModel(application) {

    private val _listMusicas = MutableLiveData<List<Musica>>(Repository.listMusicas)
    val listMusicas: LiveData<List<Musica>> = _listMusicas
    val token = PreferenceHelper.getAuthToken(getApplication<Application>().applicationContext)
    init {
        fetchMusicasFromAPI()
    }

    fun deleteMusica(position: Int) {
        viewModelScope.launch {
            if (position >= 0 && position < _listMusicas.value!!.size) {
                val musicaToDelete = _listMusicas.value!![position]
                token?.let { tkn ->
                    try {
                        val response = ApiClient.instance.deleteCancion(apiKey = tkn, id = musicaToDelete.id.toString())
                        if (response.isSuccessful) {
                            // La canción se ha borrado exitosamente de la API, ahora actualizamos la lista localmente
                            fetchMusicasFromAPI() // Llamada para actualizar la lista de canciones desde la API
                        } else {
                            // Manejar errores o respuesta no exitosa
                            withContext(Dispatchers.Main) {
                                // Actualiza la UI si es necesario, por ejemplo, mostrando un mensaje de error
                            }
                        }
                    } catch (e: Exception) {
                        // Manejar excepción, por ejemplo, problemas de red
                        withContext(Dispatchers.Main) {
                            // Actualiza la UI si es necesario
                        }
                    }
                }
            }
        }
    }

    fun addOrUpdateMusica(musica: Musica?, position: Int?) {
        viewModelScope.launch {
            val updatedList = _listMusicas.value!!.toMutableList()
            if (musica != null && position != null && position >= 0 && position < updatedList.size) {
                // Actualizar música existente
                updatedList[position] = musica
            } else if (musica != null) {
                // Agregar nueva música
                updatedList.add(musica)
            }
            _listMusicas.value = updatedList
            Repository.listMusicas = updatedList // actualiza el repositorio si es necesario
        }
    }

    private fun fetchMusicasFromAPI() {
        viewModelScope.launch(Dispatchers.IO) {
            // ahora usamos el token obtenido desde las preferencias compartidas
            if (token != null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("http://34.175.246.97/api-musica/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                val service = retrofit.create(ApiService::class.java)
                val response = service.getCanciones(token) // Usamos el token aquí

                if (response.isSuccessful && response.body()?.result == "ok") {
                    val musicasFromApi = response.body()?.canciones?.map { cancion ->
                        Musica(
                            id = cancion.id.toIntOrNull() ?: 0,
                            name = cancion.nombre,
                            artista = cancion.artista,
                            image = cancion.imagen
                        )
                    } ?: listOf()

                    withContext(Dispatchers.Main) {
                        _listMusicas.value = musicasFromApi
                        Repository.listMusicas = musicasFromApi.toMutableList()
                    }
                } else {
                }
            } else {
            }
        }
    }
}