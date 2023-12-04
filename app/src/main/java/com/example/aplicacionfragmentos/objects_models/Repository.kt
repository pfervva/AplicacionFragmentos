package com.example.aplicacionfragmentos

import com.example.aplicacionfragmentos.models.Hotel


object Repository {
    val listHotels : List<Hotel> = listOf(
        Hotel(
            "Antiguo Palacio de Atienza" , "Atienza", "Guadalajara" ,
            "975 45 54 45" , "https://www.tuscasasrurales.com/imagenes/galeria/8619_g28/ico_8619.jpg"
        ),
        Hotel(
            "La Casa del Vaquero" , "Abiada", "Cantabria" ,
            "978 65 56 65" , "https://www.tuscasasrurales.com/imagenes/galeria/9472_g98/ico_9472.jpg"
        )

    )
}