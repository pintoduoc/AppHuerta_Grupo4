package com.example.apphuerta_grupo4.data.remote

import com.example.apphuerta_grupo4.data.model.Producto

data class ProductosEmbedded(
    val productoList: List<Producto> = emptyList()
)
