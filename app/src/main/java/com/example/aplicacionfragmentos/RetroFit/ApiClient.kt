package com.example.aplicacionfragmentos.RetroFit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    //Ya que lo estoy ejecutando en un movil, necesito poner la ipv4 de mi host
    private const val BASE_URL = "http://34.175.246.97/api-musica/"

    val instance: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
