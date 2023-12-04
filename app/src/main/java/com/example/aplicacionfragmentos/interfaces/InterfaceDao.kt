package com.example.aplicacionfragmentos

import com.example.aplicacionfragmentos.models.Hotel

interface InterfaceDao {
    fun getDataHotels() : List<Hotel>

}