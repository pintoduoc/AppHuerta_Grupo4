package com.example.apphuerta_grupo4.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.apphuerta_grupo4.R
import com.example.apphuerta_grupo4.navigation.Screen
import com.example.apphuerta_grupo4.ui.shared.AppScaffold
import com.example.apphuerta_grupo4.viewmodels.MainViewModel
import com.example.apphuerta_grupo4.viewmodels.ProductoViewModel
import com.example.apphuerta_grupo4.viewmodels.UsuarioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleProducto(
    mainViewModel: MainViewModel,
    productoViewModel: ProductoViewModel,
    usuarioViewModel: UsuarioViewModel,
    productoNombre: String
) {
    val productos by productoViewModel.productos.collectAsState()

    val producto = productos.firstOrNull{ it.nombre == productoNombre }

    val estadoUsuario by usuarioViewModel.estado.collectAsState()

    fun imagenIdToDrawableRes(id: Int): Int = when (id) {
        1 -> R.drawable.lechuga
        2 -> R.drawable.zanahoria
        3 -> R.drawable.tomate
        else -> R.drawable.logo_huertohogar
    }

    if (estadoUsuario.nombres.isNotBlank()) {
        AppScaffold(
            viewModel = mainViewModel,
            currentScreen = Screen.Productos
        ) { innerPadding ->

            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top
            ) {
                producto?.let { producto ->
                    Text(
                        text = producto.nombre,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Image(
                        painter = painterResource(id = imagenIdToDrawableRes(producto.imagenId)),
                        contentDescription = "Imagen Producto",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentScale = ContentScale.Fit
                    )

                    Text(
                        text = "Precio: ${producto.precio}",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.secondary
                    )

                    Text(
                        text = "Descripción: ${producto.descripcion}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                } ?: run {
                    CircularProgressIndicator()
                    Text(
                        text = "Cargando producto...",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }

}
