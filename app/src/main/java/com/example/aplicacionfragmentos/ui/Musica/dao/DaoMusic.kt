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

    //Esto es para la futura base de datos que hablamos con rooms, ya he intentado pero viendo que no
    //me salia lo deje con el comit funcional del 1.4
    override fun getDataHotels(): List<Musica> = Repository.listMusicas
}
