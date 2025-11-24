package com.example.apphuerta_grupo4.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
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
                .fillMaxSize()
                .padding(16.dp)
                .testTag("pagina_resumen_cuenta")
        ) {
            if (estado.nombres.isNotBlank()) {
                Text("Resumen de tu cuenta", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.testTag("texto_resumen"))
                Text("Nombres: ${estado.nombres}", modifier = Modifier.testTag("resumen_nombres"))
                Text("Apellidos: ${estado.apellidos}", modifier = Modifier.testTag("resumen_apellidos"))
                Text("Correo: ${estado.correo}", modifier = Modifier.testTag("resumen_correo"))
                Text("Dirección: ${estado.direccion}", modifier = Modifier.testTag("resumen_direccion"))
                Text("Contraseña: ${"*".repeat(estado.clave.length)}", modifier = Modifier.testTag("resumen_clave_oculta"))
                Text("Términos y Condiciones: ${if (estado.aceptaTerminos) "Aceptados" else "No Aceptados"}", modifier = Modifier.testTag("resumen_terminos"))
            } else {
                Text(
                    text = "No hay datos disponibles, intenta crear una cuenta o iniciar sesión",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                        .padding(innerPadding)
                        .testTag("mensaje_sin_datos")
                )
            }
        }
    }
}
