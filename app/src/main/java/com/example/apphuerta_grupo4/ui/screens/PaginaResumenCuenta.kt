
package com.example.apphuerta_grupo4.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.apphuerta_grupo4.navigation.Screen
import com.example.apphuerta_grupo4.ui.shared.AppScaffold
import com.example.apphuerta_grupo4.viewmodels.MainViewModel
import com.example.apphuerta_grupo4.viewmodels.UsuarioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaginaResumenCuenta(
    viewModel: MainViewModel,
    usuarioViewModel: UsuarioViewModel
) {
    val estado by usuarioViewModel.estado.collectAsState()

    AppScaffold(
        viewModel = viewModel,
        currentScreen = Screen.ResumenCuenta
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text("Resumen de tu cuenta", style = MaterialTheme.typography.headlineMedium)
            Text("Nombres: ${estado.nombres}")
            Text("Apellidos: ${estado.apellidos}")
            Text("Correo: ${estado.correo}")
            Text("Dirección: ${estado.direccion}")
            Text("Contraseña: ${"*".repeat(estado.clave.length)}")
            Text("Términos y Condiciones: ${if (estado.aceptaTerminos) "Aceptados" else "No Aceptados"}")
        }
    }
}
