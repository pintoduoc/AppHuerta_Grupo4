package com.example.apphuerta_grupo4.ui.screens

package com.example.apphuerta_grupo4.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apphuerta_grupo4.R

// -------------------------------
// 1. Modelo de datos
// -------------------------------
data class Producto(
    val nombre: String,
    val descripcion: String,
    val precio: String,
    val imagen: Int
)

// -------------------------------
// 2. Lista de productos
// -------------------------------
val listaProductos = listOf(
    Producto("Lechuga", "Lechuga fresca y orgánica", "$1.200 / unidad", R.drawable.lechuga),
    Producto("Tomate", "Tomates maduros del huerto", "$1.800 / kilo", R.drawable.tomate),
    Producto("Zanahoria", "Zanahorias recién cosechadas", "$1.000 / kilo", R.drawable.zanahoria)
)

// -------------------------------
// 3. Pantalla principal
// -------------------------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApartadoProducto(
    onVolver: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Productos del Huerto") },
                navigationIcon = {
                    IconButton(onClick = onVolver) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            items(listaProductos) { producto ->
                TarjetaProducto(producto)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

// -------------------------------
// 4. Tarjeta de producto
// -------------------------------
@Composable
fun TarjetaProducto(producto: Producto) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(12.dp)
        ) {
            Image(
                painter = painterResource(id = producto.imagen),
                contentDescription = producto.nombre,
                modifier = Modifier
                    .size(90.dp)
                    .padding(4.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = producto.nombre,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Text(
                    text = producto.descripcion,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = producto.precio,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(6.dp))
                Button(onClick = { /* Aquí podrías agregar detalles o carrito */ }) {
                    Text("Ver más")
                }
            }
        }
    }
}
