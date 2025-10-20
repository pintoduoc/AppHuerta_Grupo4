package com.example.apphuerta_grupo4.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.apphuerta_grupo4.data.AppDatabase
import com.example.apphuerta_grupo4.data.Producto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class ProductoViewModel(application: Application) : AndroidViewModel(application) {

    private val productoDao = AppDatabase.getDatabase(application).productoDao()
    
    val productos: StateFlow<List<Producto>> = productoDao.getAll()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    fun getProductoPorNombre(nombre: String): Flow<Producto> {
        return productoDao.getByNombre(nombre)
    }
}
