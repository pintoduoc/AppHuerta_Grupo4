package com.example.apphuerta_grupo4.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apphuerta_grupo4.R
import com.example.apphuerta_grupo4.data.model.Producto
import com.example.apphuerta_grupo4.data.remote.RetrofitClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProductoViewModel : ViewModel(){

    private val _productos = MutableStateFlow<List<Producto>>(emptyList())

    val productos: StateFlow<List<Producto>> = _productos

    init {
        cargarProductos()
    }
    private fun cargarProductos() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.instance.obtenerProductos()
                Log.d("ProductoViewModel", "Respuesta API: $response")

                val lista = response._embedded?.productoList ?: emptyList()
                _productos.value = lista

                Log.d("ProductoViewModel", "Productos en VM: ${_productos.value.size}")
            } catch (e: Exception) {
                Log.e("ProductoViewModel", "Error cargando productos", e)
                _productos.value = emptyList()
            }
        }
    }

    fun getProductoPorNombre(nombre: String): Producto? {
        return _productos.value.firstOrNull { it.nombre == nombre }
    }
}
