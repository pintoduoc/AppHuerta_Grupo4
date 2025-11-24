package com.example.apphuerta_grupo4.viewmodels

import androidx.lifecycle.ViewModel
import com.example.apphuerta_grupo4.model.UsuarioErrores
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import com.example.apphuerta_grupo4.model.UsuarioUiState



class UsuarioViewModel : ViewModel() {
    companion object {
        // Expresión regular básica para validar correos electrónicos (usuario@dominio.tld)
        private val EMAIL_REGEX = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
    }
    private val _estado = MutableStateFlow(UsuarioUiState())

    val estado: StateFlow<UsuarioUiState> = _estado

    fun onNombresChange(valor: String) {
        _estado.update {it.copy(nombres = valor, errores = it.errores.copy(nombres = null))}
    }

    fun onApellidosChange(valor: String) {
        _estado.update { it.copy(apellidos = valor, errores = it.errores.copy(apellidos = null)) }

    }


    fun onCorreoChange(valor: String) {
        _estado.update { it.copy(correo = valor, errores = it.errores.copy(correo = null)) }
    }

    fun onClaveChange(valor: String) {
        _estado.update { it.copy(clave = valor, errores = it.errores.copy(clave = null)) }
    }

    fun onDireccionChange(valor: String) {
        _estado.update { it.copy(direccion = valor, errores = it.errores.copy(direccion = null)) }
    }

    fun onAceptarTerminosChange(valor: Boolean) {
        _estado.update { it.copy(aceptaTerminos = valor) }
    }

    fun validarFormulario(): Boolean {
        val estadoActual = _estado.value
        val errores = UsuarioErrores(
            nombres = if (estadoActual.nombres.isBlank()) "Campo obligatorio" else null,
            apellidos = if (estadoActual.apellidos.isBlank()) "Campo obligatorio" else null,
            // Reemplazamos contains("@") por la validación con expresión regular
            correo = if (!EMAIL_REGEX.matches(estadoActual.correo)) "Correo Invalido" else null,
            clave = if (estadoActual.clave.length < 6) "Debe contener al menos 6 caracteres" else null,
            direccion = if (estadoActual.direccion.isBlank()) "Campo obligatorio" else null
            )
        val hayErrores = listOfNotNull(
            errores.nombres,
            errores.apellidos,
            errores.correo,
            errores.clave,
            errores.direccion
        ).isNotEmpty()

        _estado.update { it.copy(errores = errores) }

        return !hayErrores
    }

}