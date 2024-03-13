package com.example.aplicacionfragmentos.ui.Registro.inicio

data class InicioSesionRequest(
    val email: String,
    val password: String,
    val nombre: String,
    val disponible: String,
    val imagen: String
)