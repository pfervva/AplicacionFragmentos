package com.example.aplicacionfragmentos.ui.Musica

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplicacionfragmentos.ui.Musica.dao.DaoMusic
import com.example.aplicacionfragmentos.ui.Musica.models.Musica
import kotlinx.coroutines.launch

class MusicaViewModel : ViewModel() {

    private val daoMusic = DaoMusic.myDao
    private val _listMusicas = MutableLiveData<List<Musica>>(daoMusic.getDataHotels())
    val listMusicas: LiveData<List<Musica>> = _listMusicas

    fun deleteMusica(position: Int) {
        viewModelScope.launch {
            if (position >= 0 && position < _listMusicas.value!!.size) {
                val updatedList = _listMusicas.value!!.toMutableList().apply {
                    removeAt(position)
                }
                _listMusicas.value = updatedList
            }
        }
    }

    fun addOrUpdateMusica(musica: Musica?, position: Int?) {
        viewModelScope.launch {
            val updatedList = _listMusicas.value!!.toMutableList()
            if (musica != null && position != null && position >= 0 && position < updatedList.size) {
                // Actualizar música existente
                updatedList[position] = musica
                _listMusicas.value = updatedList
            } else if (musica != null) {
                // Agregar nueva música
                updatedList.add(musica)
                _listMusicas.value = updatedList
            }
        }
    }
}
