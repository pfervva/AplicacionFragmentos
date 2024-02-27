package com.example.aplicacionfragmentos.RetroFit

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("endp/auth")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<LoginResponse>
}