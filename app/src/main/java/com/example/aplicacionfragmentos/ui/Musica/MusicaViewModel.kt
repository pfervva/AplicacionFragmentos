package com.example.aplicacionfragmentos.ui.Musica

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.aplicacionfragmentos.RetroFit.ApiClient
import com.example.aplicacionfragmentos.RetroFit.ApiService
import com.example.aplicacionfragmentos.RetroFit.NuevaCancionRequest
import com.example.aplicacionfragmentos.objects_models.Repository
import com.example.aplicacionfragmentos.ui.Musica.models.Musica
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.aplicacionfragmentos.RetroFit.PreferenceHelper
import java.io.ByteArrayOutputStream
import java.net.HttpURLConnection
import java.net.URL


class MusicaViewModel(application: Application) : AndroidViewModel(application) {

    private val _listMusicas = MutableLiveData<List<Musica>>(Repository.listMusicas)
    val listMusicas: LiveData<List<Musica>> = _listMusicas
    val token = PreferenceHelper.getAuthToken(getApplication<Application>().applicationContext)
    init {
        fetchMusicasFromAPI()
    }

    fun deleteMusica(position: Int) {
        viewModelScope.launch {
            _listMusicas.value?.let { listMusicas ->
                if (position >= 0 && position < listMusicas.size) {
                    val musicaId = listMusicas[position].id
                    val token = PreferenceHelper.getAuthToken(getApplication<Application>().applicationContext)

                    token?.let { tkn ->
                        val response = ApiClient.instance.deleteCancion(musicaId, tkn)
                        if (response.isSuccessful && response.body()?.result == "ok") {
                            // La canción se eliminó exitosamente de la API, ahora actualiza tu lista local
                            val updatedList = listMusicas.toMutableList().apply {
                                removeAt(position)
                            }
                            _listMusicas.value = updatedList
                            Repository.listMusicas = updatedList // Actualiza el repositorio si es necesario
                            // Opcional: Recargar la lista desde la API para asegurarse de que los datos están sincronizados
                            fetchMusicasFromAPI()
                        } else {
                            // Manejar error si la petición no fue exitosa
                        }
                    }
                }
            }
        }
    }

    private fun convertImageToBase64(imageUrl: String): String {
        val url = URL(imageUrl)
        val connection = url.openConnection() as HttpURLConnection
        connection.doInput = true
        connection.connect()
        val inputStream = connection.inputStream
        val bitmap = BitmapFactory.decodeStream(inputStream)

        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        val byteArray = outputStream.toByteArray()
        val base64String = Base64.encodeToString(byteArray, Base64.DEFAULT)

        // Asegúrate de usar el prefijo correcto según el formato de tu imagen.
        return "data:image/jpg;base64,$base64String"
    }


    fun addMusica(nombre: String, artista: String, imageUrl: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val imageBase64 = withContext(Dispatchers.IO) { convertImageToBase64(imageUrl) }
                val nuevaCancionRequest = NuevaCancionRequest(nombre, artista, imageBase64)

                token?.let { tkn ->
                    val response = ApiClient.instance.addCancion(tkn, nuevaCancionRequest)
                    if (response.isSuccessful && response.body()?.result == "ok insercion") {
                        fetchMusicasFromAPI()
                    } else {
                        Log.e("AddSong", "Failed to add song: ${response.errorBody()?.string()}")
                    }
                } ?: Log.e("AddSong", "API key (token) is null")
            } catch (e: Exception) {
                Log.e("AddSong", "Exception in addMusica: ${e.message}", e)
            }
        }
    }



    private fun fetchMusicasFromAPI() {
        viewModelScope.launch(Dispatchers.IO) {
            // ahora usamos el token obtenido desde las preferencias compartidas
            if (token != null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("http://192.168.1.24/api-musica/")
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