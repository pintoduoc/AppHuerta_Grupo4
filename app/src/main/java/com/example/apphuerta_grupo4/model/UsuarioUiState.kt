package com.example.apphuerta_grupo4.model


data class UsuarioUiState(
        val nombres: String = "",
        val apellidos: String = "",
        val correo: String = "",
        val clave: String  = "",
        val direccion: String = "",
        val aceptaTerminos: Boolean = false,
        val errores: UsuarioErrores = UsuarioErrores()
        )