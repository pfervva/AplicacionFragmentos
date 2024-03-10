package com.example.aplicacionfragmentos.RetroFit

import com.example.aplicacionfragmentos.ui.Registro.inicio.InicioSesionRequest
import com.example.aplicacionfragmentos.ui.Registro.inicio.InicioSesionResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("endp/auth")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<LoginResponse>
    @POST("endp/registro")
    suspend fun registrarUsuario(@Body registroRequest: InicioSesionRequest): Response<InicioSesionResponse>
    @GET("endp/cancion")
    suspend fun getCanciones(@Header("api-key") apiKey: String): Response<CancionesResponse>
}