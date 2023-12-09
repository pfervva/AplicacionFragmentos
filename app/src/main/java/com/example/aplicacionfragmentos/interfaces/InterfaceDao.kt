package com.example.aplicacionfragmentos.interfaces

import com.example.aplicacionfragmentos.models.Hotel

interface InterfaceDao {
    fun getDataHotels() : List<Hotel>

}