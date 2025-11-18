package com.example.apphuerta_grupo4.data.remote

import retrofit2.http.GET

interface ApiService {
    @GET("productos")
    suspend fun obtenerProductos(): ProductosResponse
}