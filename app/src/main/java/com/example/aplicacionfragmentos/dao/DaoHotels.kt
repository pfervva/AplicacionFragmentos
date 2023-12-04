package com.example.aplicacionfragmentos

import com.example.aplicacionfragmentos.InterfaceDao
import com.example.aplicacionfragmentos.Repository
import com.example.aplicacionfragmentos.models.Hotel

class DaoHotels private constructor(): InterfaceDao {
    companion object {
        val myDao: DaoHotels by lazy{ //lazy delega a un primer acceso
            DaoHotels() //Me creo sólo este objeto una vez.
        }
    }
    //Método que accede a la BBDD y devuelve todos los datos
    override fun getDataHotels(): List<Hotel> = Repository. listHotels
}
