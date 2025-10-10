package com.example.apphuerta_grupo4.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apphuerta_grupo4.ui.screens.ApartadoProducto
import com.example.apphuerta_grupo4.ui.screens.PaginaConfiguracion
import com.example.apphuerta_grupo4.ui.screens.PaginaInicio
import com.example.apphuerta_grupo4.ui.screens.PaginaRegistro
import com.example.apphuerta_grupo4.ui.screens.PaginaResumenCuenta
import com.example.apphuerta_grupo4.viewmodels.MainViewModel
import com.example.apphuerta_grupo4.viewmodels.UsuarioViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val mainViewModel: MainViewModel = viewModel()

    LaunchedEffect(Unit) {
        mainViewModel.setNavController(navController)
    }
    val usuarioViewModel: UsuarioViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable("pagina_inicio") {
            PaginaInicio(viewModel = mainViewModel, usuarioViewModel = usuarioViewModel)
        }
        composable("pagina_configuracion") {
            PaginaConfiguracion(viewModel = mainViewModel, usuarioViewModel = usuarioViewModel)
        }
        composable("apartado_productos") {
            ApartadoProducto(viewModel = mainViewModel, usuarioViewModel = usuarioViewModel)
        }
        composable("pagina_registro") {
            PaginaRegistro(viewModel = mainViewModel, usuarioViewModel = usuarioViewModel)
        }
        composable("resumen_cuenta") {
            PaginaResumenCuenta(viewModel = mainViewModel, usuarioViewModel = usuarioViewModel)
        }


    }
}