
package com.example.apphuerta_grupo4.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.apphuerta_grupo4.navigation.Screen
import com.example.apphuerta_grupo4.ui.shared.AppScaffold
import com.example.apphuerta_grupo4.viewmodels.MainViewModel
import com.example.apphuerta_grupo4.viewmodels.UsuarioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaginaRegistro(
    viewModel: MainViewModel,
    usuarioViewModel: UsuarioViewModel
) {
    val estado by usuarioViewModel.estado.collectAsState()

    AppScaffold(
        viewModel = viewModel,
        currentScreen = Screen.Registro
    ) { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            //Campo Nombres:
            OutlinedTextField(
                value = estado.nombres,
                onValueChange = usuarioViewModel::onNombresChange,
                label = { Text("Nombres") },
                isError = estado.errores.nombres != null,
                supportingText = {
                    estado.errores.nombres?.let {
                        Text(it, color = MaterialTheme.colorScheme.error)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            //Campo Apellidos
            OutlinedTextField(
                value = estado.apellidos,
                onValueChange = usuarioViewModel::onApellidosChange,
                label = { Text("Apellidos") },
                isError = estado.errores.apellidos != null,
                supportingText = {
                    estado.errores.apellidos?.let {
                        Text(it, color = MaterialTheme.colorScheme.error)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            //Campo correo
            OutlinedTextField(
                value = estado.correo,
                onValueChange = usuarioViewModel::onCorreoChange,
                label = { Text("Correo Electronico") },
                isError = estado.errores.correo != null,
                supportingText = {
                    estado.errores.correo?.let {
                        Text(it, color = MaterialTheme.colorScheme.error)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            //Campo clave
            OutlinedTextField(
                value = estado.clave,
                onValueChange = usuarioViewModel::onClaveChange,
                label = { Text("Contraseña") },
                isError = estado.errores.clave != null,
                supportingText = {
                    estado.errores.clave?.let {
                        Text(it, color = MaterialTheme.colorScheme.error)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            //Campo Direccion
            OutlinedTextField(
                value = estado.direccion,
                onValueChange = usuarioViewModel::onDireccionChange,
                label = { Text("Dirección") },
                isError = estado.errores.direccion != null,
                supportingText = {
                    estado.errores.direccion?.let {
                        Text(it, color = MaterialTheme.colorScheme.error)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
            //Checkbox: Aceptar terminos y condiciones
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = estado.aceptaTerminos,
                    onCheckedChange = usuarioViewModel::onAceptarTerminosChange
                )
                Spacer(Modifier.width(8.dp))
                Text("Acepto los terminos y condiciones")
            }
            //Boton Enviar
            Button(
                onClick = {

                    if (usuarioViewModel.validarFormulario()) {
                        viewModel.navigateTo(Screen.ResumenCuenta)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrar")
            }
        }
    }
}
