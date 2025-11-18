package com.example.apphuerta_grupo4.data.model

import com.google.gson.annotations.SerializedName

data class Producto(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val precio: String,
    val imagenId: Int
)