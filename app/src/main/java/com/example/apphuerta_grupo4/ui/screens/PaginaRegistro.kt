package com.example.apphuerta_grupo4.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.apphuerta_grupo4.navigation.Screen
import com.example.apphuerta_grupo4.viewmodels.MainViewModel
import com.example.apphuerta_grupo4.viewmodels.UsuarioViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaginaRegistro(
    viewModel: MainViewModel,
    usuarioViewModel: UsuarioViewModel
) {
    val estado by usuarioViewModel.estado.collectAsState()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(text = "Menú", modifier = Modifier.padding(5.dp))
                //Items del Drawer:
                NavigationDrawerItem(
                    label = { Text("Inicio") },
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() }
                        viewModel.navigateTo(Screen.Home)
                    }
                )
                NavigationDrawerItem(
                    label = { Text("Configuracion") },
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() }
                        viewModel.navigateTo(Screen.Settings)
                    }
                )
                NavigationDrawerItem(
                    label = { Text("Productos") },
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() }
                        viewModel.navigateTo(Screen.Productos)
                    }
                )

                NavigationDrawerItem(
                    label = { Text("Registro") },
                    selected = true,
                    onClick = {
                        scope.launch { drawerState.close() }
                        viewModel.navigateTo(Screen.Registro)
                    }
                )
                NavigationDrawerItem(
                    label = {Text("Resumen Cuenta")},
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() }
                        viewModel.navigateTo(Screen.ResumenCuenta)
                    }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Tienda Huerto Hogar") },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch { drawerState.open() }
                        }) {
                            Icon(imageVector = Icons.Default.Menu, contentDescription = "Menú")
                        }
                    }
                )
            }
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
}