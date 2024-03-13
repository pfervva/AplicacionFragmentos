package com.example.aplicacionfragmentos.RetroFit

import com.example.aplicacionfragmentos.ui.Registro.inicio.InicioSesionRequest
import com.example.aplicacionfragmentos.ui.Registro.inicio.InicioSesionResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface ApiService {
    @POST("endp/auth")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<LoginResponse>
    @POST("endp/registro")
    suspend fun registrarUsuario(@Body registroRequest: InicioSesionRequest): Response<InicioSesionResponse>
    @GET("endp/cancion")
    suspend fun getCanciones(@Header("api-key") apiKey: String): Response<CancionesResponse>
    @DELETE("endp/cancion")
    suspend fun deleteCancion(@Query("id") idCancion: Int, @Header("api-key") apiKey: String): Response<GenericResponse>
    @PUT("endp/cancion")
    suspend fun updateCancion(@Query("id") idCancion: Int, @Header("api-key") apiKey: String, @Body cancion: NuevaCancionRequest): Response<GenericResponse>
    @POST("endp/cancion")
    suspend fun addCancion(@Header("api-key") apiKey: String, @Body nuevaCancion: NuevaCancionRequest): Response<NuevaCancionResponse>}
