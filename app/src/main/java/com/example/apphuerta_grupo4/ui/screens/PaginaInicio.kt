package com.example.apphuerta_grupo4.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.apphuerta_grupo4.R
import com.example.apphuerta_grupo4.navigation.Screen
import com.example.apphuerta_grupo4.viewmodels.MainViewModel
import com.example.apphuerta_grupo4.viewmodels.UsuarioViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaginaInicio(
    viewModel: MainViewModel,
    usuarioViewModel: UsuarioViewModel
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(text = "Menú", modifier = Modifier.padding(5.dp))
                //Items del Drawer:
                NavigationDrawerItem(
                    label = {Text("Inicio")},
                    selected = true,
                    onClick = {
                        scope.launch {drawerState.close()}
                        viewModel.navigateTo(Screen.Home)
                    }
                )
                NavigationDrawerItem(
                    label = {Text("Configuracion")},
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() }
                        viewModel.navigateTo(Screen.Settings)
                    }
                )
                NavigationDrawerItem(
                    label = {Text("Productos")},
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() }
                        viewModel.navigateTo(Screen.Productos)
                    }
                )

                NavigationDrawerItem(
                    label = {Text("Registro")},
                    selected = false,
                    onClick = {
                        scope.launch {drawerState.close()}
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
        Scaffold (
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
        ) {innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Text(
                    text = "¡Bienvenido a la tienda virtual de Huerto Hogar!",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleLarge
                )
                Image(
                    painter = painterResource(id = R.drawable.logo_huertohogar),
                    contentDescription = "Logo Huerto Hogar",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    contentScale = ContentScale.Fit
                )

                Button(
                    onClick = { viewModel.navigateTo(Screen.Registro) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text("Empezar la Experiencia")
                }

                Button(
                    onClick = { /* Aca debería mandar a la pantalla de Inicio de Sesion */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text("¿Ya tienes una cuenta?")
                }
            }
        }
    }

}