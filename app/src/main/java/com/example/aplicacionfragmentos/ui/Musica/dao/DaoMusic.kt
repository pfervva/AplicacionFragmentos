package com.example.aplicacionfragmentos.ui.Musica.dao

import com.example.aplicacionfragmentos.objects_models.Repository
import com.example.aplicacionfragmentos.ui.Musica.interfaces.InterfaceDao
import com.example.aplicacionfragmentos.ui.Musica.models.Musica

class DaoMusic private constructor(): InterfaceDao {
    companion object {
        val myDao: DaoMusic by lazy{ //lazy delega a un primer acceso
            DaoMusic() //Me creo sólo este objeto una vez.
        }
    }
    //Método que accede a la BBDD y devuelve todos los datos
    override fun getDataHotels(): List<Musica> = Repository.listMusicas
}
